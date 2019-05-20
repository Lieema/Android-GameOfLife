package com.example.gameoflife

class DataItem(var isSelected: Boolean) {
    fun ChangeState()
    {
        isSelected = !isSelected
    }
}