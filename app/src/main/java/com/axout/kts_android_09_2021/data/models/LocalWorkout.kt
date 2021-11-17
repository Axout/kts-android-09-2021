package com.axout.kts_android_09_2021.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = LocalWorkoutContract.TABLE_NAME,
    indices = [
        Index(LocalWorkoutContract.Columns.NAME)
    ]
)
data class LocalWorkout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = LocalWorkoutContract.Columns.ID)
    val id: Long,
    @ColumnInfo(name = LocalWorkoutContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = LocalWorkoutContract.Columns.DISTANCE)
    val distance: Float,
    @ColumnInfo(name = LocalWorkoutContract.Columns.KUDOS)
    val kudos: Int
)