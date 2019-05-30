package com.example.gameoflife

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        info_fragment_button.setOnClickListener { callRuleFragment() }
    }

    private fun callRuleFragment(){
        val nextFrag = RulesFragment()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, nextFrag)
            ?.addToBackStack(null)
            ?.commit()
    }
}
