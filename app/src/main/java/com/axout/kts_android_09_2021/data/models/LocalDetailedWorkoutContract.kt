package com.axout.kts_android_09_2021.data.models

object LocalDetailedWorkoutContract {
    const val TABLE_NAME = "detailedWorkouts"

    object Columns {
        const val ID = "id"
        const val NAME = "name"
        const val DISTANCE = "distance"
        const val TIME = "time"
        const val AVG_SPEED = "avgSpeed"
        const val MAX_SPEED = "maxSpeed"
        const val ELEVATION_GAIN = "elevationGain"
        const val MAX_ELEVATION = "maxElevation"
        const val PHOTO = "photo"
    }
}
