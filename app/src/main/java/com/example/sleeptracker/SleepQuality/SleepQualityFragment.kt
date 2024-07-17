package com.example.sleeptracker.SleepQuality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sleeptracker.Database.SleepDatabase
import com.example.sleeptracker.R
import com.example.sleeptracker.SleepTracker.SleepTrackerViewmodel
import com.example.sleeptracker.SleepTracker.SleepTrackerViewmodelFactory
import com.example.sleeptracker.databinding.FragmentSleepQualityBinding

class SleepQualityFragment : Fragment() {
   private lateinit var binding:    FragmentSleepQualityBinding
   private val args: SleepQualityFragmentArgs by navArgs()
    private lateinit var viewmodel: SleepQualityViewmodel
    private lateinit var viewmodelFactory: SleepQualityViewmodelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).dao

        viewmodelFactory = SleepQualityViewmodelFactory(args.sleepNightKey,dataSource)
        viewmodel = ViewModelProvider(this, viewmodelFactory)[(SleepQualityViewmodel::class.java)]

        viewmodel.navigateToSleepTacker.observe(viewLifecycleOwner){
            if(it==true){
                this.findNavController().navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                viewmodel.doneNavigating()
            }
        }
        binding.qualityZeroImage.setOnClickListener{
            viewmodel.rating(0)
        }

        binding.qualityOneImage.setOnClickListener{
            viewmodel.rating(1)
        }

        binding.qualityTwoImage.setOnClickListener{
            viewmodel.rating(2)
        }

        binding.qualityThreeImage.setOnClickListener{
            viewmodel.rating(3)
        }

        binding.qualityFourImage.setOnClickListener{
            viewmodel.rating(4)
        }

        binding.qualityFiveImage.setOnClickListener{
            viewmodel.rating(5)
        }


        return binding.root
    }

}