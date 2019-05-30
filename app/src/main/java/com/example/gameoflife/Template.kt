package com.example.gameoflife

class Template(
    coordinates: MutableList<Pair<Int,Int>> = arrayListOf()
) {
    var coordinates = coordinates

    fun add(coordinate: Pair<Int, Int>) {
        coordinates.add(coordinate)
    }
}