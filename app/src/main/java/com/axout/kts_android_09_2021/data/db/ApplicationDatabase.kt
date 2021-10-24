package com.axout.kts_android_09_2021.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axout.kts_android_09_2021.data.models.DetailedWorkout
import com.axout.kts_android_09_2021.data.models.Workout

@Database(
    entities = [
        Workout::class,
        DetailedWorkout::class
    ], version = ApplicationDatabase.DB_VERSION
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun detailedWorkoutDao(): DetailedWorkoutDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app-database"
    }
}