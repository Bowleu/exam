package com.bowleu.exam

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bowleu.exam.coroutines.throttleFirst
import com.bowleu.exam.coroutines.throttleLatest
import com.bowleu.exam.kotlin.StartTimeDelegate
import com.bowleu.exam.kotlin.findInt
import com.bowleu.exam.kotlin.shakerSort
import com.bowleu.exam.rx.DataProvider
import com.bowleu.exam.rx.MyFragment
import com.bowleu.exam.rx.retrofit.RetrofitProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }
    private val compositeDisposable = CompositeDisposable()
    private var networkDisposable: Disposable? = null
    private var timerDisposable: Disposable? = null
    private var subjectDisposable: Disposable? = null
    private var zipDisposable: Disposable? = null
    private val subject = PublishSubject.create<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_layout)
        findViewById<Button>(R.id.network_button).setOnClickListener { onNetworkButtonClicked() }
        val editText = findViewById<EditText>(R.id.debounce_edit)
        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
               subject.onNext(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MyFragment())
            .addToBackStack(null)
            .commit()

        createTimer()
        createZip()
        createEditTextLog()
    }

    private fun createZip() {
        zipDisposable?.let {
            if (compositeDisposable.remove(it)) it.dispose()
        }
        zipDisposable = Observable.zip(
            DataProvider.getServer1().onErrorReturnItem(emptyList()),
            DataProvider.getServer2().onErrorReturnItem(emptyList())
            // Можно убрать onErrorReturnItem чтобы ошибка проходила
        ) { list1, list2 ->
            list1 + list2
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { result -> Log.d(TAG, "Combined: $result") },
                { error -> {}
                    // Ничего не выводим
                }
            )
    }

    private fun onNetworkButtonClicked() {
        networkDisposable?.let {
            if (compositeDisposable.remove(it)) it.dispose()
        }
        networkDisposable = RetrofitProvider.api.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { user ->
                findViewById<TextView>(R.id.network_text).text = user.toString()
            }
    }

    private fun createTimer() {
        timerDisposable?.let {
            if (compositeDisposable.remove(it)) it.dispose()
        }
        timerDisposable = Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { time ->
                findViewById<TextView>(R.id.timer_text).text = time.toString()
            }
    }

    private fun createEditTextLog() {
        subjectDisposable?.let {
            if (compositeDisposable.remove(it)) it.dispose()
        }
        subjectDisposable = subject
            .debounce(3, TimeUnit.SECONDS)
            .distinctUntilChanged()
            .observeOn(Schedulers.io())
            .subscribe { text ->
                Log.d(TAG, "User has typed: $text")
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}