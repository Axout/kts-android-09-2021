package com.axout.kts_android_09_2021.detailed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.networking.DetailedWorkoutRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailedWorkoutViewModel: ViewModel() {

    private val repository = DetailedWorkoutRepository()
    private val detailedWorkoutLiveData = MutableLiveData<DetailedWorkout>()

    private var currentGetDetailedWorkoutJob: Job? = null

    val detailedWorkout: LiveData<DetailedWorkout>
        get() = detailedWorkoutLiveData

    fun getActivityById(id: Long, include_all_efforts: Boolean) {
        currentGetDetailedWorkoutJob?.cancel()
        currentGetDetailedWorkoutJob = viewModelScope.launch {
            runCatching {
                Log.d("tag", "getActivityById()")
                repository.getById(id, include_all_efforts)
            }.onSuccess {
                Log.d("tag", "onSuccess: $it")
                detailedWorkoutLiveData.postValue(it)
            }.onFailure {
                Log.d("tag", "onFailure: $it")
                detailedWorkoutLiveData.postValue(null)
            }
        }
    }
}