package com.example.sleeptracker.SleepTracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.sleeptracker.Database.SleepDao
import com.example.sleeptracker.Database.SleepNight
import com.example.sleeptracker.formatNights
import kotlinx.coroutines.launch

class SleepTrackerViewmodel(private val dao: SleepDao, application: Application) :
    AndroidViewModel(application) {
    private val tonight = MutableLiveData<SleepNight?>()


    private val nights = dao.getAllNights()

    val nightsString = nights.map { nights ->
        formatNights(nights, application.resources)
    }


    private val _navigateToSleepQuality=MutableLiveData<SleepNight?>()

    val navigateToSleepQuality: LiveData<SleepNight?>
        get() = _navigateToSleepQuality


    init {
        initializeTonight()
    }

    fun doneNavigating(){
        _navigateToSleepQuality.value=null
    }

    private fun initializeTonight() {
        viewModelScope.launch { tonight.value = getTonightFromDatabase() }

    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        var night = dao.getNight()
        if (night?.endTime != night?.startTime) {
            night = null
        }
        return night

    }

    fun onStartTracking() {
        viewModelScope.launch {
            val newNight = SleepNight()
            upsert(newNight)
            tonight.value = getTonightFromDatabase()
        }

    }

    private suspend fun upsert(night: SleepNight) {
        dao.upsert(night)
    }

    fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTime=System.currentTimeMillis()
            upsert(oldNight)
            _navigateToSleepQuality.value=oldNight
        }

    }

    fun onClear() {
        viewModelScope.launch {
            dao.clear()
            tonight.value = null
        }

    }


}