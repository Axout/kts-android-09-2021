package com.axout.kts_android_09_2021.data.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.data.DetailedWorkoutRepository
import com.axout.kts_android_09_2021.data.models.DetailedWorkout
import com.axout.kts_android_09_2021.data.models.Workout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailedWorkoutViewModel : ViewModel() {

    private val detailedWorkoutRepository = DetailedWorkoutRepository()

    private val saveSuccess = Channel<Unit>(Channel.BUFFERED)
    private val saveError = Channel<String>(Channel.BUFFERED)

    val saveSuccessFlow: Flow<Unit>
        get() = saveSuccess.receiveAsFlow()

    val saveErrorFlow: Flow<String>
        get() = saveError.receiveAsFlow()

    fun save(
        id: Long,
        name: String,
        distance: Float,
        time: Int,
        avgSpeed: Float,
        maxSpeed: Float,
        elevationGain: Float,
        maxElevation: Float,
        photo: String
    ) {

        val detailedWorkout = DetailedWorkout(
            id = id,
            name = name,
            distance = distance,
            time = time,
            avgSpeed = avgSpeed,
            maxSpeed = maxSpeed,
            elevationGain = elevationGain,
            maxElevation = maxElevation,
            photo = photo
        )

        viewModelScope.launch {
            try {
                detailedWorkoutRepository.saveWorkout(detailedWorkout)
                saveSuccess.send(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "detailedWorkout save error")
                saveError.send("detailedWorkout save error")
            }
        }
    }

    fun loadWorkoutById(workout: Workout) {
        viewModelScope.launch {
            try {
                detailedWorkoutRepository.getDetailedWorkoutById(workout.id)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }
}