package com.axout.kts_android_09_2021.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axout.kts_android_09_2021.data.models.LocalWorkout
import kotlinx.coroutines.flow.Flow
import com.axout.kts_android_09_2021.data.models.LocalWorkoutContract

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkouts(localWorkouts: List<LocalWorkout>)

    @Query("SELECT * FROM ${LocalWorkoutContract.TABLE_NAME}")
    fun observeAllWorkouts(): Flow<List<LocalWorkout>>
}