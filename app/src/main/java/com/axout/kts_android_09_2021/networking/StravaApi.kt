package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.main.models.AthleteActivity
import com.axout.kts_android_09_2021.detailed.DetailedActivity
import com.axout.kts_android_09_2021.main.models.DetailedAthlete
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StravaApi {
    @GET("athlete/activities")
    suspend fun getLoggedInAthleteActivities(
        @Query("before") before: Int,
        @Query("after") after: Int
    ): List<AthleteActivity>

    @GET("activities/{id}")
    suspend fun getActivityById(
        @Path("id") id: Long,
        @Query("include_all_efforts") include_all_efforts: Boolean
    ): DetailedActivity

    @GET("athlete")
    suspend fun getLoggedInAthlete(): DetailedAthlete
}