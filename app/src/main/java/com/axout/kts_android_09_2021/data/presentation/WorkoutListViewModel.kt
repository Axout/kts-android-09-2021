package com.axout.kts_android_09_2021.data.presentation

import androidx.lifecycle.ViewModel
import com.axout.kts_android_09_2021.data.LocalWorkoutRepository
import com.axout.kts_android_09_2021.data.models.LocalWorkout
import kotlinx.coroutines.flow.Flow

class WorkoutListViewModel : ViewModel() {

    private val localWorkoutRepository = LocalWorkoutRepository()

//    private val workoutsMutableFlow = MutableStateFlow<List<AthleteActivity>>(emptyList())

    val workoutsFlow: Flow<List<LocalWorkout>>
        get() = localWorkoutRepository.observe()
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