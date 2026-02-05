package com.bowleu.exam.android.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bowleu.exam.R
import com.bowleu.exam.android.RouterOwner

class ScreenFragment : Fragment(R.layout.router_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
            (requireActivity() as RouterOwner).router.next()
        }
        view.findViewById<Button>(R.id.btnPrev).setOnClickListener {
            (requireActivity() as RouterOwner).router.back()
        }
    }
}