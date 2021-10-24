package com.axout.kts_android_09_2021.data

import com.axout.kts_android_09_2021.data.db.Database
import com.axout.kts_android_09_2021.data.models.DetailedWorkout

class DetailedWorkoutRepository {

    private val detailedWorkoutDao = Database.instance.detailedWorkoutDao()

    suspend fun saveWorkout(detailedWorkout: DetailedWorkout) {
        detailedWorkoutDao.insertDetailedWorkout(detailedWorkout)
    }

    suspend fun getDetailedWorkoutById(id: Long): DetailedWorkout? {
        return detailedWorkoutDao.getDetailedWorkoutById(id)
    }
}