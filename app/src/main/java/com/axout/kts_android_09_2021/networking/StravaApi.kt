package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.networking.models.AthleteActivity
import retrofit2.http.GET
import retrofit2.http.Query

interface StravaApi {
    @GET("athlete/activities")
    suspend fun getLoggedInAthleteActivities(
        @Query("before") before: Int,
        @Query("after") after: Int
    ): List<AthleteActivity>
}