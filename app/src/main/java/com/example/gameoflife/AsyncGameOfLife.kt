package com.example.gameoflife

import android.os.AsyncTask
import androidx.core.view.get

class AsyncGameOfLife(private var frag: GameFragment) : AsyncTask<Void, Void, Void>() {

    //only one argument in params
       override fun doInBackground(vararg params: Void?): Void? {
        while (frag.isRunning) {
            Thread.sleep(1000)
            publishProgress()
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Void?) {
        frag.addStep()
        //board loads the value for next step
        val board = Array(frag.data.size,
            init = {Array(frag.data[0].adapter.count,
                init = {false})})

        //compute next values in board
        for (j in 0 until frag.data.size)
        {
            for (i in 0 until frag.data[j].adapter.count) {
                val cell = (frag.data[j].adapter.getItem(i) as DataItem)
                val list = mutableListOf<DataItem>()

                //add neighbours
                //left
                if (i > 0)
                    list.add(frag.data[j].adapter.getItem(i - 1) as DataItem)
                //right
                if (i < frag.data[0].adapter.count - 1)
                    list.add(frag.data[j].adapter.getItem(i + 1) as DataItem)
                //up
                if (j > 0)
                    list.add(frag.data[j - 1].adapter.getItem(i) as DataItem)
                //down
                if (j < frag.data.size - 1)
                    list.add(frag.data[j + 1].adapter.getItem(i) as DataItem)
                //up-left
                if (i > 0 && j > 0)
                    list.add(frag.data[j - 1].adapter.getItem(i - 1) as DataItem)
                //up-right
                if (i < frag.data[0].adapter.count - 1 && j > 0)
                    list.add(frag.data[j - 1].adapter.getItem(i + 1) as DataItem)
                //down-left
                if (i > 0 && j < frag.data.size - 1)
                    list.add(frag.data[j + 1].adapter.getItem(i - 1) as DataItem)
                //down-right
                if (i < frag.data[0].adapter.count - 1 && j < frag.data.size - 1)
                    list.add(frag.data[j + 1].adapter.getItem(i + 1) as DataItem)

                board[j][i] = updateCell(list, cell).isSelected
            }
        }

        if (frag.isRunning) {
            //copy values in data
            for (j in 0 until frag.data.size) {
                for (i in 0 until frag.data[j].adapter.count) {
                    val cell = (frag.data[j].adapter.getItem(i) as DataItem)
                    if (cell.isSelected != board[j][i]) {
                        cell.enable()
                        frag.data[j][i].callOnClick()
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

        if (countLiving == 3)
            return true
        if (countLiving == 2)
            return null
        return false

    }

    //update one cell, depending of its neighbours
    private fun updateCell(listNeighbours: MutableList<DataItem>, cell: DataItem) : DataItem {
        val isLiving : Boolean = isLiving(listNeighbours) ?: return DataItem(cell.isSelected)
        return DataItem(isLiving)
    }
}