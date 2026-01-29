package com.bowleu.exam.rx

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowleu.exam.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MyFragment : Fragment(R.layout.recycler_fragment) {
    private val disposables = CompositeDisposable()
    private lateinit var adapter: MyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)

        adapter = MyAdapter(listOf("One", "Two", "Three"))
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        disposables.add(adapter.clickSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { position ->
                Toast.makeText(requireContext(), "Clicked: $position", Toast.LENGTH_SHORT).show()
            })
    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }
}