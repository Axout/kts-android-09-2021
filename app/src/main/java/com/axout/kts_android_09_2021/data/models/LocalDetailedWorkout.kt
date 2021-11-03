package com.axout.kts_android_09_2021.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = LocalDetailedWorkoutContract.TABLE_NAME,
    indices = [
        Index(LocalDetailedWorkoutContract.Columns.NAME)
    ]
)
data class LocalDetailedWorkout(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.DISTANCE)
    val distance: Float,
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.TIME)
    val time: Int,
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.AVG_SPEED)
    val avgSpeed: Float,
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.MAX_SPEED)
    val maxSpeed: Float,
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.ELEVATION_GAIN)
    val elevationGain: Float,
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.MAX_ELEVATION)
    val maxElevation: Float,
    @ColumnInfo(name = LocalDetailedWorkoutContract.Columns.PHOTO)
    val photo: String?
)