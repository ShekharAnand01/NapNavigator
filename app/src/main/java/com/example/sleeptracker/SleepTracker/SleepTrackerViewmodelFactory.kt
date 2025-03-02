package com.example.sleeptracker.SleepTracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sleeptracker.Database.SleepDao

class SleepTrackerViewmodelFactory(
    private val dao: SleepDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewmodel::class.java)) {
            return SleepTrackerViewmodel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

