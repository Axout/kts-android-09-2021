package com.axout.kts_android_09_2021.data.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.data.LocalWorkoutRepository
import com.axout.kts_android_09_2021.data.models.LocalWorkout
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LocalWorkoutViewModel : ViewModel() {

    private val localWorkoutRepository = LocalWorkoutRepository()

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
        kudos: Int
    ) {

        val localWorkout = LocalWorkout(
            id = id,
            name = name,
            distance = distance,
            kudos = kudos
        )

        viewModelScope.launch {
            try {
                Log.d("tag","workout save success")
                localWorkoutRepository.save(localWorkout)
                saveSuccess.send(Unit)
            } catch (t: Throwable) {
                Log.d("tag","workout save error")
                saveError.send("workout save error")
            }
        }
    }
}