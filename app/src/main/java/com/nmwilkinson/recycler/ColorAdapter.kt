package com.nmwilkinson.recycler

import android.support.v7.util.DiffUtil
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.UUID

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    private var prevColors = listOf<Pair<String, Int>>()
    private val colors = ArrayList<Pair<String, Int>>()

    private val diff = object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                prevColors[oldItemPosition].first == colors[newItemPosition].first

        override fun getOldListSize(): Int = prevColors.size

        override fun getNewListSize(): Int = colors.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return prevColors[oldItemPosition].second == colors[newItemPosition].second
        }
    }

    fun insertColor(color: Int) {
        prevColors = colors.toMutableList()

        colors.add(0, Pair(UUID.randomUUID().toString(), color))

        DiffUtil.calculateDiff(diff, true).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_cell, parent, false))
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        (holder.itemView as CardView).setCardBackgroundColor(colors[position].second)
    }

    fun clearData() {
        val count = colors.size
        colors.clear()
        (0 until count).forEach { notifyItemRemoved(0) }
    }

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
