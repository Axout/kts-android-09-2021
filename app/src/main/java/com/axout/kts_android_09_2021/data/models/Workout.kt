package com.axout.kts_android_09_2021.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = WorkoutContract.TABLE_NAME,
    indices = [
        Index(WorkoutContract.Columns.NAME)
    ]
)
data class Workout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = WorkoutContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = WorkoutContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = WorkoutContract.Columns.DISTANCE)
    val distance: Float,
    @ColumnInfo(name = WorkoutContract.Columns.KUDOS)
    val kudos: Int
)