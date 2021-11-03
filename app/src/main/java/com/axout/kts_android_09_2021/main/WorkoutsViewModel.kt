package com.axout.kts_android_09_2021.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.networking.WorkoutsRepository
import com.axout.kts_android_09_2021.main.models.Workout
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WorkoutsViewModel: ViewModel() {

    private val repository = WorkoutsRepository()

    private val workoutsLiveData = MutableLiveData<List<Workout>>(emptyList())

    private var currentGetWorkoutsJob: Job? = null

    val workoutsList: LiveData<List<Workout>>
        get() = workoutsLiveData

    fun getListWorkouts(before: Int, after: Int) {
        currentGetWorkoutsJob?.cancel()
        currentGetWorkoutsJob = viewModelScope.launch {
            runCatching {
                repository.getWorkouts(before, after)
            }.onSuccess {
                workoutsLiveData.postValue(it)
            }.onFailure {
                workoutsLiveData.postValue(emptyList())
            }
        }
    }
}