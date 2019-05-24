package com.example.gameoflife

fun isLiving(listCell: MutableList<DataItem>) : Boolean? {
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
fun updateCell(listCell: MutableList<DataItem>, cell: DataItem) : DataItem {
    val isLiving : Boolean = isLiving(listCell) ?: return cell
    cell.isSelected = isLiving
    return cell
}