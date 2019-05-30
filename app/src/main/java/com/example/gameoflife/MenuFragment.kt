package com.example.gameoflife

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu_fragment_button_play.setOnClickListener { callPlayFragment() }
        menu_fragment_button_info.setOnClickListener { callInfoFragment() }
    }

    private fun callPlayFragment(){
        val nextFrag = GameFragment()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, nextFrag)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun callInfoFragment(){
        val nextFrag = InfoFragment()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, nextFrag)
            ?.addToBackStack(null)
            ?.commit()
    }
}
