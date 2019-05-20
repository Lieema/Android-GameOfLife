package com.example.gameoflife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*

class Fragment_Game : Fragment() {

    private var data : MutableList<ListView> = mutableListOf()
    private var nbStep : Int = 0
    private var isRunning: Boolean = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_game, game_layout, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.post { fillData() }

        fragment_game_play_button.text = "Play"
        fragment_game_play_button.setOnClickListener{ ButtonCliked() }
        fragement_game_text_step.text = nbStep.toString()
    }

    fun Pause()
    {
        isRunning = false
        for (i in 0.. (data.size - 1))
        {
            for (j in 0..(data[i].adapter.count - 1))
                (data[i].adapter.getItem(j) as DataItem).Enable()
        }

        fragment_game_play_button.text = "Play"
    }

    fun Play()
    {
        isRunning = true
        for (i in 0.. (data.size - 1))
        {
            for (j in 0..(data[i].adapter.count - 1))
                (data[i].adapter.getItem(j) as DataItem).Disable()
        }

        fragment_game_play_button.text = "Pause"

    }

    fun ButtonCliked()
    {
        if (isRunning)
            Pause()
        else
            Play()
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
                list.add(DataItem(false))

            val l: ListView = ListView(context)
            l.x = (i * itemSize).toFloat() * dp
            l.y = 0f
            l.adapter = ListItemAdapter(activity, list)

            data.add(l)
            fragment_game_list_layout.addView(l, (25f * dp).toInt(), 0)
        }
    }
}