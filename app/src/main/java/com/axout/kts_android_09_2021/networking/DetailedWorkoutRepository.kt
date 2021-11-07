package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.data.db.Database
import com.axout.kts_android_09_2021.data.models.LocalDetailedWorkout
import com.axout.kts_android_09_2021.detailed.DetailedWorkout

class DetailedWorkoutRepository {

    private val detailedWorkoutDao = Database.instance.detailedWorkoutDao()

    suspend fun getById(online: Boolean, id: Long): LocalDetailedWorkout? {
        return if (online) {
            getByIdFromNetwork(id)
        } else {
            getByIdFromDataBase(id)
        }
    }

    private suspend fun getByIdFromDataBase(id: Long): LocalDetailedWorkout? {
        return detailedWorkoutDao.getDetailedWorkoutById(id)
    }

    private suspend fun getByIdFromNetwork(id: Long): LocalDetailedWorkout {
        val detailedWorkout = Networking.stravaApi.getActivityById(id, true)
        cashData(id, detailedWorkout)
        return mapToLocalDetailedWorkout(id, detailedWorkout)
    }

    private suspend fun cashData(id: Long, detailedWorkout: DetailedWorkout) {
        detailedWorkoutDao.insertDetailedWorkout(mapToLocalDetailedWorkout(id, detailedWorkout))
    }

    private fun mapToLocalDetailedWorkout(
        id: Long,
        detailedWorkout: DetailedWorkout
    ): LocalDetailedWorkout {

        return LocalDetailedWorkout(
            id,
            detailedWorkout.name,
            detailedWorkout.distance,
            detailedWorkout.time,
            detailedWorkout.avgSpeed,
            detailedWorkout.maxSpeed,
            detailedWorkout.elevationGain,
            detailedWorkout.maxElevation,
            detailedWorkout.photos.primary?.urls?.bigPhoto
        )
    }
}