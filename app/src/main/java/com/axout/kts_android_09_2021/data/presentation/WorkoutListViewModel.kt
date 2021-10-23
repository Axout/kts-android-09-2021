package com.axout.kts_android_09_2021.data.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.data.WorkoutRepository
import com.axout.kts_android_09_2021.data.models.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class WorkoutListViewModel : ViewModel() {

    private val workoutRepository = WorkoutRepository()

//    private val workoutsMutableFlow = MutableStateFlow<List<Workout>>(emptyList())

    val workoutsFlow: Flow<List<Workout>>
        get() = workoutRepository.observeAllWorkouts()
//        get() = workoutsMutableFlow.asStateFlow()

//    fun loadList() {
//        viewModelScope.launch {
//            try {
//                workoutsMutableFlow.value = workoutRepository.getAllWorkouts()
//            } catch (t: Throwable) {
//                Timber.e(t, "workout list error")
//                workoutsMutableFlow.value = emptyList()
//            }
//        }
//    }
}