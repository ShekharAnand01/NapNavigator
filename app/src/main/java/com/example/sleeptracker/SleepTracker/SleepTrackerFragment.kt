package com.example.sleeptracker.SleepTracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sleeptracker.Database.SleepDatabase
import com.example.sleeptracker.R
import com.example.sleeptracker.databinding.FragmentSleepTrackerBinding


class SleepTrackerFragment : Fragment() {

    private lateinit var viewmodel: SleepTrackerViewmodel
    private lateinit var viewmodelFactory: SleepTrackerViewmodelFactory
    private lateinit var binding: FragmentSleepTrackerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).dao

        viewmodelFactory = SleepTrackerViewmodelFactory(dataSource, application)
        viewmodel = ViewModelProvider(this, viewmodelFactory)[(SleepTrackerViewmodel::class.java)]

        viewmodel.navigateToSleepQuality.observe(viewLifecycleOwner) { navigate ->
            navigate?.let {
                val action =
                    SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(
                        navigate.nightId
                    )
                findNavController().navigate(action)
                viewmodel.doneNavigating()
            }
        }
        binding.textview.text = viewmodel.nightsString.toString()
        binding.startButton.setOnClickListener {
            viewmodel.onStartTracking()
        }
        binding.stopButton.setOnClickListener {
            viewmodel.onStopTracking()
        }
        binding.clearButton.setOnClickListener {
            viewmodel.onClear()
        }
        return binding.root


    }


}

