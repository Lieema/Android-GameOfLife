package com.example.gameoflife

import android.app.LauncherActivity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*

class Fragment_Game : Fragment() {

    private var data : Array<ListView> = arrayOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_game, game_layout, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.post { fillData() }
    }

    fun fillData()
    {
        val dp = resources.displayMetrics.density

        val width = (fragment_game_list_layout.measuredWidth / dp).toInt()
        val height = (fragment_game_list_layout.measuredHeight / dp).toInt()

        val itemSize = 25

        for (i in 0..(width / itemSize - 1)) {

            val list = mutableListOf<DataItem>()

            for (j in 0..(height / itemSize - 1))
                list.add(DataItem(j % 2 == 0))

            val l: ListView = ListView(context)
            l.x = (i * itemSize).toFloat() * dp
            l.y = 0f
            l.adapter = ListItemAdaptater(activity, list)

            fragment_game_list_layout.addView(l, (25f * dp).toInt(), 0)
        }
    }
}