package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.main.models.DetailedAthlete

class DetailedAthleteRepository {

    suspend fun getLoggedInAthlete(): DetailedAthlete {
        return Networking.stravaApi.getLoggedInAthlete()
    }
}