package com.axout.kts_android_09_2021.data.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.data.LocalDetailedWorkoutRepository
import com.axout.kts_android_09_2021.data.models.LocalDetailedWorkout
import com.axout.kts_android_09_2021.detailed.DetailedWorkout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class LocalDetailedWorkoutViewModel : ViewModel() {

    private val localDetailedWorkoutRepository = LocalDetailedWorkoutRepository()

    private val localDetailedWorkoutEmpty = LocalDetailedWorkout(0,"",0F,0,0F,0F,0F,0F,"")
    private val workoutMutableFlow = MutableStateFlow<LocalDetailedWorkout?>(localDetailedWorkoutEmpty)

    private val saveSuccess = Channel<Unit>(Channel.BUFFERED)
    private val saveError = Channel<String>(Channel.BUFFERED)

    val workoutFlow: Flow<LocalDetailedWorkout?>
        get() = workoutMutableFlow.asStateFlow()

    val saveSuccessFlow: Flow<Unit>
        get() = saveSuccess.receiveAsFlow()

    val saveErrorFlow: Flow<String>
        get() = saveError.receiveAsFlow()

    fun save(
        id: Long,
        detailedWorkout: DetailedWorkout
    ) {

        val localDetailedWorkout = LocalDetailedWorkout(
            id = id,
            name = detailedWorkout.name,
            distance = detailedWorkout.distance,
            time = detailedWorkout.time,
            avgSpeed = detailedWorkout.avgSpeed,
            maxSpeed = detailedWorkout.maxSpeed,
            elevationGain = detailedWorkout.elevationGain,
            maxElevation = detailedWorkout.maxElevation,
            photo = detailedWorkout.photos.primary?.urls?.bigPhoto
        )

        viewModelScope.launch {
            try {
                localDetailedWorkoutRepository.save(localDetailedWorkout)
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
                workoutMutableFlow.value = localDetailedWorkoutRepository.getById(id)
            } catch (t: Throwable) {
                Timber.e(t)
                //workoutMutableFlow.value = detailedWorkoutEmpty
            }
        }
    }
}