package com.example.gameoflife

import android.os.Bundle
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
        private const val timeTick: Long = 800
    }

    val data : MutableList<ListView> = mutableListOf()
    var isRunning: Boolean = false

    private var nbStep : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, game_layout, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.post { fillData() }

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

        run()

        fragment_game_play_button.text = getString(R.string.Pause)
    }

    private fun run() {
        val cellManager = CellManager()
        val threadedExecutor = ThreadedExecutor()
        threadedExecutor.execute {
            Thread.sleep(timeTick)
            while (isRunning) {
                cellManager.onProgressUpdate(data, isRunning)
                activity?.runOnUiThread {
                    this.addStep()
                }
                Thread.sleep(timeTick)
            }
        }
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
        for (j in 0 until data.size) {
            for (i in 0 until data[j].adapter.count) {
                val cell = (data[j].adapter.getItem(i) as DataItem)
                cell.enable()
                if (cell.isSelected)
                    data[j][i].callOnClick()
            }
        }
    }

    private fun fillData()
    {
        val dp = resources.displayMetrics.density

        val width = (fragment_game_list_layout.measuredWidth / dp).toInt()
        val height = (fragment_game_list_layout.measuredHeight / dp).toInt()

        for (i in 0 until width / itemSize) {

            val dataItems = mutableListOf<DataItem>()

            for (j in 1 until height / itemSize)
                dataItems.add(DataItem(false))

            val listView = ListView(context)
            listView.x = (i * itemSize).toFloat() * dp
            listView.y = 0f
            listView.isScrollContainer = false
            listView.isVerticalScrollBarEnabled = false
            listView.adapter = ListItemAdapter(activity, dataItems)

            data.add(listView)
            fragment_game_list_layout.addView(listView, (25f * dp).toInt(), 0)
        }
    }
}