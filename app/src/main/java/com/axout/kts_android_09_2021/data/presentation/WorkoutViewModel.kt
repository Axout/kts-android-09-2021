package com.axout.kts_android_09_2021.data.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.data.WorkoutRepository
import com.axout.kts_android_09_2021.data.models.Workout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class WorkoutViewModel : ViewModel() {

    private val workoutRepository = WorkoutRepository()

    private val saveError = Channel<Int>(Channel.BUFFERED)
    private val saveSuccess = Channel<Unit>(Channel.BUFFERED)

    val saveSuccessFlow: Flow<Unit>
        get() = saveSuccess.receiveAsFlow()

    val saveErrorFlow: Flow<Int>
        get() = saveError.receiveAsFlow()

    fun save(
        id: Long,
        name: String,
        distance: Float,
        kudos: Int
    ) {

        val workout = Workout(
            id = id,
            name = name,
            distance = distance,
            kudos = kudos
        )

        viewModelScope.launch {
            try {
                Log.d("tag","workout save success")
                workoutRepository.saveWorkout(workout)
                saveSuccess.send(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "workout save error")
            }
        }
    }
}