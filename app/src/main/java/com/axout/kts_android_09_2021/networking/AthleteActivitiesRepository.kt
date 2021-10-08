package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.networking.models.AthleteActivity

class AthleteActivitiesRepository {

    suspend fun getAthleteActivities(
        before: Int,
        after: Int
    ): List<AthleteActivity> {
        return Networking.stravaApi.getLoggedInAthleteActivities(before, after)
    }
}