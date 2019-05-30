package com.example.gameoflife

class TemplateManager {
    fun getPulsarTemplate(): Template {
        val ret= Template()
        ret.add(Pair(7,7))
        ret.add(Pair(8,7))
        ret.add(Pair(9,7))
        ret.add(Pair(8,8))

        ret.add(Pair(7,11))
        ret.add(Pair(8,11))
        ret.add(Pair(9,11))
        ret.add(Pair(8,10))
        return ret
    }

    fun getGliderTemplate(): Template {
        val ret= Template()
        ret.add(Pair(1,0))
        ret.add(Pair(1,2))
        ret.add(Pair(2,1))
        ret.add(Pair(2,2))
        ret.add(Pair(3,1))
        return ret
    }
}