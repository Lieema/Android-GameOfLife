package com.example.gameoflife

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.FragmentActivity

class ListItemAdapter(private val context: FragmentActivity?, private val data: MutableList<DataItem>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // first let us retrieve the item at the specified position
        val currentItem: DataItem = getItem(position)

        // now we build a view first step, acquire a LayoutInflater
        val layoutInflater = LayoutInflater.from(context)

        val root =  layoutInflater.inflate(R.layout.fragment_game_list_item, parent,false)

        root.setOnClickListener {
            if (currentItem.ChangeState()) {
                if (currentItem.isSelected)
                    root.setBackgroundColor(Color.BLACK)
                else
                    root.setBackgroundColor(Color.WHITE)
            }
        }

        return root
    }

    override fun getItem(position: Int): DataItem {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

}