package com.example.gameoflife

import android.widget.ListView
import androidx.core.view.get

class CellManager {
    companion object {
        private const val aliveCount = 3
        private const val nothingCount = 2
    }

    fun onProgressUpdate(data: MutableList<ListView>, isRunning: Boolean) {
        //board loads the value for next step
        val board = Array(data.size,
            init = {Array(data[0].adapter.count,
                init = {false})})

        //compute next values in board
        for (j in 0 until data.size)
        {
            for (i in 0 until data[j].adapter.count) {
                val cell = (data[j].adapter.getItem(i) as DataItem)
                val list = mutableListOf<DataItem>()

                //add neighbours
                //left
                if (i > 0)
                    list.add(data[j].adapter.getItem(i - 1) as DataItem)
                //right
                if (i < data[0].adapter.count - 1)
                    list.add(data[j].adapter.getItem(i + 1) as DataItem)
                //up
                if (j > 0)
                    list.add(data[j - 1].adapter.getItem(i) as DataItem)
                //down
                if (j < data.size - 1)
                    list.add(data[j + 1].adapter.getItem(i) as DataItem)
                //up-left
                if (i > 0 && j > 0)
                    list.add(data[j - 1].adapter.getItem(i - 1) as DataItem)
                //up-right
                if (i < data[0].adapter.count - 1 && j > 0)
                    list.add(data[j - 1].adapter.getItem(i + 1) as DataItem)
                //down-left
                if (i > 0 && j < data.size - 1)
                    list.add(data[j + 1].adapter.getItem(i - 1) as DataItem)
                //down-right
                if (i < data[0].adapter.count - 1 && j < data.size - 1)
                    list.add(data[j + 1].adapter.getItem(i + 1) as DataItem)

                board[j][i] = updateCell(list, cell).isSelected
            }
        }

        if (isRunning) {
            //copy values in data
            for (j in 0 until data.size) {
                for (i in 0 until data[j].adapter.count) {
                    val cell = (data[j].adapter.getItem(i) as DataItem)
                    if (cell.isSelected != board[j][i]) {
                        cell.enable()
                        data[j][i].callOnClick()
                        cell.disable()
                    }
                }
            }
        }
    }

    private fun isLiving(listCell: MutableList<DataItem>) : Boolean? {
        var countLiving = 0

        for (cell in listCell) {
            if (cell.isSelected)
                ++countLiving
        }

        if (countLiving == aliveCount)
            return true
        if (countLiving == nothingCount)
            return null
        return false

    }

    //update one cell, depending of its neighbours
    private fun updateCell(listNeighbours: MutableList<DataItem>, cell: DataItem) : DataItem {
        val isLiving : Boolean = isLiving(listNeighbours) ?: return DataItem(cell.isSelected)
        return DataItem(isLiving)
    }
}