package com.axout.kts_android_09_2021.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = DetailedWorkoutContract.TABLE_NAME,
    indices = [
        Index(DetailedWorkoutContract.Columns.NAME)
    ]
)
data class DetailedWorkout(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DetailedWorkoutContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.DISTANCE)
    val distance: Float,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.TIME)
    val time: Int,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.AVG_SPEED)
    val avgSpeed: Float,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.MAX_SPEED)
    val maxSpeed: Float,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.ELEVATION_GAIN)
    val elevationGain: Float,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.MAX_ELEVATION)
    val maxElevation: Float,
    @ColumnInfo(name = DetailedWorkoutContract.Columns.PHOTO)
    val photo: String?
)