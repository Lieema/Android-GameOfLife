package com.example.gameoflife

class DataItem(var isSelected: Boolean) {
    private var isEnable = true

    fun ChangeState(): Boolean
    {
        if (isEnable) {
            isSelected = !isSelected
            return true
        }
        return false
    }

    fun Enable()
    {
        isEnable = true
    }

    fun Disable()
    {
        isEnable = false
    }
}