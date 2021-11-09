package com.axout.kts_android_09_2021.main

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
                repository.getLoggedInAthlete()
            }.onSuccess {
                athleteLiveData.postValue(it)
            }.onFailure {
                athleteLiveData.postValue(null)
            }
        }
    }
}