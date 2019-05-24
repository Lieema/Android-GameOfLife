package com.example.gameoflife

import android.os.AsyncTask

class AsyncGameOfLife : AsyncTask<AsyncParams, Int, DataItem?>() {

    //only one argument in params
    override fun doInBackground(vararg params: AsyncParams): DataItem? {
        if (params.size != 1)
            return null
        return updateCell(params[0].listNeighbours, params[0].cell)
    }

    private fun isLiving(listCell: MutableList<DataItem>) : Boolean? {
        var countLiving : Int = 0

        for (cell in listCell) {
            if (cell.isSelected)
                ++countLiving
        }

        if (countLiving == 3)
            return true
        if (countLiving == 2)
            return null
        return false

    }

    //update one cell, depending of its neighbours
    private fun updateCell(listNeighbours: MutableList<DataItem>, cell: DataItem) : DataItem {
        val isLiving : Boolean = isLiving(listNeighbours) ?: return cell
        cell.isSelected = isLiving
        return cell
    }
}