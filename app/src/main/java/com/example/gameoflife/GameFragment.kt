package com.example.gameoflife

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {
    // Like static variable in Java
    companion object {
        private const val itemSize = 25
        private const val itemCount = 15 * 20
        private const val gridWidth = 15
    }

    val data : MutableList<ListView> = mutableListOf()
    var isRunning: Boolean = false
    var dataItems: MutableList<DataItem> = arrayListOf()

    private var nbStep : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        for (j in 1 until itemCount)
            dataItems.add(DataItem(false))
        fragment_game_recycler_view.adapter = GameOfLifeRecyclerAdapter(activity, dataItems)

        return inflater.inflate(R.layout.fragment_game, game_layout, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_game_play_button.text = getString(R.string.Play)
        fragment_game_play_button.setOnClickListener{ playPauseButtonClicked() }
        fragment_game_stop_button.text = getString(R.string.Stop)
        fragment_game_stop_button.setOnClickListener { stop() }
        fragement_game_text_step.text = nbStep.toString()
    }

    fun addStep() {
        ++nbStep
        fragement_game_text_step.text = nbStep.toString()
    }

    private fun pause()
    {
        isRunning = false
        for (i in 0 until data.size)
        {
            for (j in 0 until data[i].adapter.count)
                (data[i].adapter.getItem(j) as DataItem).enable()
        }

        fragment_game_play_button.text = getString(R.string.Play)
    }

    private fun play()
    {
        isRunning = true

        for (i in 0 until data.size)
        {
            for (j in 0 until data[i].adapter.count) {
                (data[i].adapter.getItem(j) as DataItem).disable()
            }
        }

        val gameTask = AsyncGameOfLife(this)
        gameTask.execute()

        fragment_game_play_button.text = getString(R.string.Pause)
    }

    private fun playPauseButtonClicked()
    {
        if (isRunning)
            pause()
        else
            play()
    }

    private fun stop() {
        nbStep = 0
        fragement_game_text_step.text = nbStep.toString()
        if (isRunning) {
            isRunning = false
            fragment_game_play_button.text = getString(R.string.Play)
        }
        dataItems.forEach { cell -> cell.disable() }
        fragment_game_recycler_view.adapter?.notifyDataSetChanged()
    }
}