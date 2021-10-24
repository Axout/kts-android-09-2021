package com.axout.kts_android_09_2021.data

import kotlinx.coroutines.flow.Flow
import com.axout.kts_android_09_2021.data.db.Database
import com.axout.kts_android_09_2021.data.models.Workout

class WorkoutRepository {

    private val workoutDao = Database.instance.workoutDao()

    suspend fun saveWorkout(workout: Workout) {
        workoutDao.insertWorkouts(listOf(workout))
    }

    fun observeAllWorkouts(): Flow<List<Workout>> {
        return workoutDao.observeAllWorkouts()
    }
}