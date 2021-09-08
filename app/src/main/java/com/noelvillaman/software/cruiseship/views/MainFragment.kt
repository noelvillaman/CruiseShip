package com.noelvillaman.software.cruiseship.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.noelvillaman.software.cruiseship.R

class MainFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_sky).setOnClickListener {
            (activity as MainActivity).code = "SKY"
            findNavController().navigate(R.id.action_MainFragment_to_InfoFragment)
        }

        view.findViewById<Button>(R.id.button_bliss).setOnClickListener {
            (activity as MainActivity).code = "BLISS"
            findNavController().navigate(R.id.action_MainFragment_to_InfoFragment)
        }

        view.findViewById<Button>(R.id.button_escape).setOnClickListener {
            (activity as MainActivity).code = "SCAPE"
            findNavController().navigate(R.id.action_MainFragment_to_InfoFragment)
        }
    }
}