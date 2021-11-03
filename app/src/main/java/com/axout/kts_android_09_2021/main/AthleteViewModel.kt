package com.axout.kts_android_09_2021.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.main.models.Athlete
import com.axout.kts_android_09_2021.networking.AthleteRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AthleteViewModel: ViewModel() {

    private val repository = AthleteRepository()
    private val athleteLiveData = MutableLiveData<Athlete>()

    private var currentDetailedAthleteJob: Job? = null

    val athlete: LiveData<Athlete>
        get() = athleteLiveData

    fun getLoggedInAthlete() {
        currentDetailedAthleteJob?.cancel()
        currentDetailedAthleteJob = viewModelScope.launch {
            runCatching {
                Log.d("tag", "getAthletes()")
                repository.getLoggedInAthlete()
            }.onSuccess {
                Log.d("tag", "onSuccess: $it")
                athleteLiveData.postValue(it)
            }.onFailure {
                Log.d("tag", "onFailure: $it")
                athleteLiveData.postValue(null)
            }
        }
    }
}