package com.axout.kts_android_09_2021.data

import com.axout.kts_android_09_2021.data.db.Database
import com.axout.kts_android_09_2021.data.models.LocalDetailedWorkout

class LocalDetailedWorkoutRepository {

    private val detailedWorkoutDao = Database.instance.detailedWorkoutDao()

    suspend fun save(localDetailedWorkout: LocalDetailedWorkout) {
        detailedWorkoutDao.insertDetailedWorkout(localDetailedWorkout)
    }

    suspend fun getById(id: Long): LocalDetailedWorkout? {
        return detailedWorkoutDao.getDetailedWorkoutById(id)
    }
}