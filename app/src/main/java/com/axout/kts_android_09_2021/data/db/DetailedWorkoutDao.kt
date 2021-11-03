package com.axout.kts_android_09_2021.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axout.kts_android_09_2021.data.models.LocalDetailedWorkout
import com.axout.kts_android_09_2021.data.models.DetailedWorkoutContract

@Dao
interface DetailedWorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailedWorkout(localDetailedWorkout: LocalDetailedWorkout)

    @Query("SELECT * FROM ${DetailedWorkoutContract.TABLE_NAME} WHERE ${DetailedWorkoutContract.Columns.ID} = :id")
    suspend fun getDetailedWorkoutById(id: Long): LocalDetailedWorkout?
}
