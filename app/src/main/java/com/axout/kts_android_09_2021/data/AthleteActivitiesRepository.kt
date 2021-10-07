package com.axout.kts_android_09_2021.data

import com.axout.kts_android_09_2021.data.models.AthleteActivity

class AthleteActivitiesRepository {

    suspend fun getAthleteActivities(
        before: Int,
        after: Int
    ): List<AthleteActivity> {
        return Networking.stravaApi.getLoggedInAthleteActivities(before, after)
    }
}