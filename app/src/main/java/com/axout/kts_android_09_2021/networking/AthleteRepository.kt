package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.main.models.Athlete

class AthleteRepository {

    suspend fun getLoggedInAthlete(): Athlete {
        return Networking.stravaApi.getLoggedInAthlete()
    }
}