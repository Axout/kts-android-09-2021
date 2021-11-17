package com.axout.kts_android_09_2021.detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.data.models.LocalDetailedWorkout
import com.axout.kts_android_09_2021.networking.DetailedWorkoutRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailedWorkoutViewModel: ViewModel() {

    private val repository = DetailedWorkoutRepository()
    private val localDetailedWorkoutLiveData = MutableLiveData<LocalDetailedWorkout>()

    private var currentGetDetailedWorkoutJob: Job? = null

    val detailedWorkout: LiveData<LocalDetailedWorkout>
        get() = localDetailedWorkoutLiveData

    fun getActivityById(id: Long, online: Boolean) {
        currentGetDetailedWorkoutJob?.cancel()
        currentGetDetailedWorkoutJob = viewModelScope.launch {
            runCatching {
                repository.getById(online, id)
            }.onSuccess {
                localDetailedWorkoutLiveData.postValue(it)
            }.onFailure {
                localDetailedWorkoutLiveData.postValue(null)
            }
        }
    }
}