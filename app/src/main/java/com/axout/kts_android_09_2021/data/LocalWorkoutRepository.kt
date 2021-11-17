package com.axout.kts_android_09_2021.data

import kotlinx.coroutines.flow.Flow
import com.axout.kts_android_09_2021.data.db.Database
import com.axout.kts_android_09_2021.data.models.LocalWorkout

class LocalWorkoutRepository {

    private val workoutDao = Database.instance.workoutDao()

    suspend fun save(localWorkout: LocalWorkout) {
        workoutDao.insertWorkouts(listOf(localWorkout))
    }

    fun observe(): Flow<List<LocalWorkout>> {
        return workoutDao.observeAllWorkouts()
    }
}