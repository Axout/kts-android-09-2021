package com.axout.kts_android_09_2021.data.presentation

import androidx.lifecycle.ViewModel
import com.axout.kts_android_09_2021.data.LocalWorkoutRepository
import com.axout.kts_android_09_2021.data.models.LocalWorkout
import kotlinx.coroutines.flow.Flow

class LocalWorkoutListViewModel : ViewModel() {

    private val localWorkoutRepository = LocalWorkoutRepository()

    val workoutsFlow: Flow<List<LocalWorkout>>
        get() = localWorkoutRepository.observe()
}