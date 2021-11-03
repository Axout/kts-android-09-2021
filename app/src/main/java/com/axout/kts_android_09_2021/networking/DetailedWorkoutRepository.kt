package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.detailed.DetailedWorkout

class DetailedWorkoutRepository {

    suspend fun getById(
        id: Long,
        include_all_efforts: Boolean
    ): DetailedWorkout {
        return Networking.stravaApi.getActivityById(id, include_all_efforts)
    }
}