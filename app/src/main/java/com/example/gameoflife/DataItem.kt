package com.example.gameoflife

class DataItem(var isSelected: Boolean) {
    private var isEnable = true

    fun changeState(): Boolean
    {
        if (isEnable) {
            isSelected = !isSelected
            return true
        }
        return false
    }

    fun enable()
    {
        isEnable = true
    }

    fun disable()
    {
        isEnable = false
    }
}