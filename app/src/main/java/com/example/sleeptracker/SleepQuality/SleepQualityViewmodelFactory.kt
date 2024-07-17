package com.example.sleeptracker.SleepQuality

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptracker.Database.SleepDao
import com.example.sleeptracker.SleepTracker.SleepTrackerViewmodel

class SleepQualityViewmodelFactory (private val sleepNightKey: Long, private val dao: SleepDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewmodel::class.java)) {
            return SleepQualityViewmodel(sleepNightKey,dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}