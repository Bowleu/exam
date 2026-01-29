package com.bowleu.exam.rx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.subjects.PublishSubject

class MyAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    val clickSubject: PublishSubject<Int> = PublishSubject.create()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener { clickSubject.onNext(position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        (holder.itemView as TextView).text = items[position]
    }

    override fun getItemCount() = items.size
}