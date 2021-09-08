package com.noelvillaman.software.cruiseship.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.noelvillaman.software.cruiseship.R
import com.noelvillaman.software.cruiseship.model.CruiseBliss
import com.noelvillaman.software.cruiseship.model.CruiseScape
import com.noelvillaman.software.cruiseship.model.CruiseSky
import com.noelvillaman.software.cruiseship.viewmodel.BlissViewModel
import com.noelvillaman.software.cruiseship.viewmodel.ScapeViewModel
import com.noelvillaman.software.cruiseship.viewmodel.SkyViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class FragmentInfo : Fragment() {
    @BindView(R.id.tv_error)
    internal lateinit var errorTextView: TextView

    private var unbinder: Unbinder? = null
    private var skyViewModel: SkyViewModel? = null
    private var blissViewModel : BlissViewModel? = null
    private var scapeViewModel : ScapeViewModel? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        errorTextView = view.findViewById(R.id.tv_error)
        unbinder = ButterKnife.bind(this, view)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        skyViewModel = ViewModelProvider(this).get(SkyViewModel::class.java)
        blissViewModel = ViewModelProvider(this).get(BlissViewModel::class.java)
        scapeViewModel = ViewModelProvider(this).get(ScapeViewModel::class.java)
        when {
            (activity as MainActivity).code.equals("SKY", true) -> {
                observeViewModel()
            }
            (activity as MainActivity).code.equals("BLISS", true) -> {
                observeBlissViewModel()
            }
            (activity as MainActivity).code.equals("SCAPE", true) -> {
                observeScapeViewModel()
            }
        }
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_InfoFragment_to_MainFragment)
        }
    }

    private fun observeViewModel() {
        val nameObserver = Observer<CruiseSky> { cruise ->
            if (cruise != null) {
                ship_name.text = "Ship name: ${cruise.shipName}"
                ship_crew.text = "Crew: ${cruise.shipFacts?.crew}"
                ship_capacity.text = "Capacity: ${cruise.shipFacts?.passengerCapacity}"
                ship_inagural_date.text = "Inagural Date: ${cruise.shipFacts?.inauguralDate}"
            }
        }
        skyViewModel?.getCruiseSky()?.observe(viewLifecycleOwner, nameObserver)
    }

    private fun observeBlissViewModel() {
        val nameObserver = Observer<CruiseBliss> { cruise ->
            if (cruise != null) {
                ship_name.text = "Ship name: ${cruise.shipName}"
                ship_crew.text = "Crew: ${cruise.shipFacts?.crew}"
                ship_capacity.text = "Capacity: ${cruise.shipFacts?.passengerCapacity}"
                ship_inagural_date.text = "Inagural Date: ${cruise.shipFacts?.inauguralDate}"
            }
        }
        skyViewModel?.getCruiseBliss()?.observe(viewLifecycleOwner, nameObserver)
    }

    private fun observeScapeViewModel() {
        val nameObserver = Observer<CruiseScape> { cruise ->
            if (cruise != null) {
                ship_name.text = "Ship name: ${cruise.shipName}"
                ship_crew.text = "Crew: ${cruise.shipFacts?.crew}"
                ship_capacity.text = "Capacity: ${cruise.shipFacts?.passengerCapacity}"
                ship_inagural_date.text = "Inagural Date: ${cruise.shipFacts?.inauguralDate}"
            }
        }
        skyViewModel?.getCruiseScape()?.observe(viewLifecycleOwner, nameObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (unbinder != null) {
            unbinder?.unbind()
            unbinder = null
        }
    }
}