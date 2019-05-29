package com.example.gameoflife

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GameOfLifeRecyclerAdapter(private val context: Context?, private val data: MutableList<DataItem>) :
    RecyclerView.Adapter<GameOfLifeRecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cellView: View = itemView.findViewById(R.id.cellView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cellView = LayoutInflater.from(context).inflate(R.layout.cell, parent, false)
        return ViewHolder(cellView)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCell = data[position]
        holder.cellView.tag = position
        if (currentCell.isSelected) {
            holder.cellView.setBackgroundColor(Color.YELLOW)
        }
        else {
            holder.cellView.setBackgroundColor(Color.WHITE)
        }
    }

}