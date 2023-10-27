package com.example.myjoke.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myjoke.R

class CommonDataRecyclerAdapter<E>():
    RecyclerView.Adapter<CommonDataRecyclerAdapter<E>.CommonViewHolder<E>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonDataRecyclerAdapter<E>.CommonViewHolder<E> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_data_item, parent, false)
        return CommonViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: CommonDataRecyclerAdapter<E>.CommonViewHolder<E>,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class CommonViewHolder<E>(view: View): RecyclerView.ViewHolder(view) {

    }
}

