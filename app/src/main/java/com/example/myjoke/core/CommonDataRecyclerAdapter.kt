package com.example.myjoke.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myjoke.R
import com.example.myjoke.presentation.UiState
import com.example.myjoke.presentation.views.CorrectTextView

class CommonDataRecyclerAdapter<E>():
    RecyclerView.Adapter<CommonDataRecyclerAdapter<E>.CommonViewHolder<E>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonDataRecyclerAdapter<E>.CommonViewHolder<E> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_data_item, parent, false)
        return CommonViewHolder(view)
    }

    private val list: ArrayList<UiState> = ArrayList()

    override fun onBindViewHolder(
        holder: CommonDataRecyclerAdapter<E>.CommonViewHolder<E>,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun show(data:List<UiState>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged() //todo устарело
    }

    inner class CommonViewHolder<E>(view: View): RecyclerView.ViewHolder(view) {
        private val textView = itemView.findViewById<CorrectTextView>(R.id.textView)
        fun bind(data: UiState){
            data.show(textView)
        }
    }
}

