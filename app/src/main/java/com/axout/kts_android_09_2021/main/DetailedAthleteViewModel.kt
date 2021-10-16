package com.axout.kts_android_09_2021.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.main.models.DetailedAthlete
import com.axout.kts_android_09_2021.networking.DetailedAthleteRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailedAthleteViewModel: ViewModel() {

    private val repository = DetailedAthleteRepository()
    private val detailedAthleteLiveData = MutableLiveData<DetailedAthlete>()

    private var currentDetailedAthleteJob: Job? = null

    val detailedAthlete: LiveData<DetailedAthlete>
        get() = detailedAthleteLiveData

    fun getLoggedInAthlete() {
        currentDetailedAthleteJob?.cancel()
        currentDetailedAthleteJob = viewModelScope.launch {
            runCatching {
                Log.d("tag", "getAthletes()")
                repository.getLoggedInAthlete()
            }.onSuccess {
                Log.d("tag", "onSuccess: $it")
                detailedAthleteLiveData.postValue(it)
            }.onFailure {
                Log.d("tag", "onFailure: $it")
                detailedAthleteLiveData.postValue(null)
            }
        }
    }
}