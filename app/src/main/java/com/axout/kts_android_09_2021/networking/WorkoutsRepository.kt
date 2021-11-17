package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.main.models.Workout

class WorkoutsRepository {

    suspend fun getWorkouts(
        before: Int,
        after: Int
    ): List<Workout> {
        return Networking.stravaApi.getLoggedInAthleteActivities(before, after)
    }
}