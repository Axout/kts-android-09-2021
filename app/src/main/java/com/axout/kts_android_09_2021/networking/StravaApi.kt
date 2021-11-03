package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.main.models.Workout
import com.axout.kts_android_09_2021.detailed.DetailedWorkout
import com.axout.kts_android_09_2021.main.models.DetailedAthlete
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StravaApi {
    @GET("athlete/activities")
    suspend fun getLoggedInAthleteActivities(
        @Query("before") before: Int,
        @Query("after") after: Int
    ): List<Workout>

    @GET("activities/{id}")
    suspend fun getActivityById(
        @Path("id") id: Long,
        @Query("include_all_efforts") include_all_efforts: Boolean
    ): DetailedWorkout

    @GET("athlete")
    suspend fun getLoggedInAthlete(): DetailedAthlete
}