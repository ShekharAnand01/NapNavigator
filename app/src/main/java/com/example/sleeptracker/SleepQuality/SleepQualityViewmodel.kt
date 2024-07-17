package com.example.sleeptracker.SleepQuality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleeptracker.Database.SleepDao
import kotlinx.coroutines.launch

class SleepQualityViewmodel(private val sleepNightKey: Long,private val dao: SleepDao): ViewModel() {
    private var _navigateToSleepTracker=MutableLiveData<Boolean>()
    val navigateToSleepTacker:LiveData<Boolean>
        get() = _navigateToSleepTracker

    fun doneNavigating(){
        _navigateToSleepTracker.value=false
    }

    fun rating(quality: Int){
        viewModelScope.launch {
            val night=dao.get(sleepNightKey)?:return@launch
            night.rating=quality
            dao.upsert(night)
            _navigateToSleepTracker.value=true
        }


    }
}