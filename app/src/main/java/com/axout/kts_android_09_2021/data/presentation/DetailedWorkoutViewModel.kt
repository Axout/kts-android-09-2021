package com.axout.kts_android_09_2021.data.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.data.DetailedWorkoutRepository
import com.axout.kts_android_09_2021.data.models.DetailedWorkout
import com.axout.kts_android_09_2021.detailed.DetailedActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailedWorkoutViewModel : ViewModel() {

    private val detailedWorkoutRepository = DetailedWorkoutRepository()

    private val detailedWorkoutEmpty = DetailedWorkout(0,"",0F,0,0F,0F,0F,0F,"")
    private val workoutMutableFlow = MutableStateFlow<DetailedWorkout?>(detailedWorkoutEmpty)

    private val saveSuccess = Channel<Unit>(Channel.BUFFERED)
    private val saveError = Channel<String>(Channel.BUFFERED)

    val workoutFlow: Flow<DetailedWorkout?>
        get() = workoutMutableFlow.asStateFlow()

    val saveSuccessFlow: Flow<Unit>
        get() = saveSuccess.receiveAsFlow()

    val saveErrorFlow: Flow<String>
        get() = saveError.receiveAsFlow()

    fun save(
        id: Long,
        detailedActivity: DetailedActivity
    ) {

        val detailedWorkout = DetailedWorkout(
            id = id,
            name = detailedActivity.name,
            distance = detailedActivity.distance,
            time = detailedActivity.time,
            avgSpeed = detailedActivity.avgSpeed,
            maxSpeed = detailedActivity.maxSpeed,
            elevationGain = detailedActivity.elevationGain,
            maxElevation = detailedActivity.maxElevation,
            photo = detailedActivity.photos.primary?.urls?.bigPhoto
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

    fun loadWorkoutById(id: Long) {
        viewModelScope.launch {
            try {
                workoutMutableFlow.value = detailedWorkoutRepository.getDetailedWorkoutById(id)
            } catch (t: Throwable) {
                Timber.e(t)
                //workoutMutableFlow.value = detailedWorkoutEmpty
            }
        }
    }
}