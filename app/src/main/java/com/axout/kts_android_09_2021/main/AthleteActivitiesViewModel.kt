package com.axout.kts_android_09_2021.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.networking.AthleteActivitiesRepository
import com.axout.kts_android_09_2021.main.models.AthleteActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AthleteActivitiesViewModel: ViewModel() {

    private val repository = AthleteActivitiesRepository()

    private val athleteActivitiesLiveData = MutableLiveData<List<AthleteActivity>>(emptyList())
    private val isLoadingLiveData = MutableLiveData(false)

    private var currentGetAthleteActivitiesJob: Job? = null

    val athleteActivitiesList: LiveData<List<AthleteActivity>>
        get() = athleteActivitiesLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun getListActivities(before: Int, after: Int) {
        isLoadingLiveData.postValue(true)
        currentGetAthleteActivitiesJob?.cancel()
        currentGetAthleteActivitiesJob = viewModelScope.launch {
            runCatching {
                Log.d("tag", "getAthletes()")
                repository.getAthleteActivities(before, after)
            }.onSuccess {
                Log.d("tag", "onSuccess: $it")
                isLoadingLiveData.postValue(false)
                athleteActivitiesLiveData.postValue(it)
            }.onFailure {
                Log.d("tag", "onFailure: $it")
                isLoadingLiveData.postValue(false)
                athleteActivitiesLiveData.postValue(emptyList())
            }
        }
    }
}