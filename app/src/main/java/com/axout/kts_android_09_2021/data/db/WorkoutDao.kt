package com.axout.kts_android_09_2021.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axout.kts_android_09_2021.data.models.Workout
import kotlinx.coroutines.flow.Flow
import com.axout.kts_android_09_2021.data.models.WorkoutContract

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkouts(workouts: List<Workout>)

    @Query("SELECT * FROM ${WorkoutContract.TABLE_NAME}")
    fun observeAllWorkouts(): Flow<List<Workout>>
}