package com.axout.kts_android_09_2021.data

//import android.util.Patterns
import kotlinx.coroutines.flow.Flow
import com.axout.kts_android_09_2021.data.db.Database
import com.axout.kts_android_09_2021.data.models.Workout

class WorkoutRepository {

    private val workoutDao = Database.instance.workoutDao()

    suspend fun saveWorkout(workout: Workout) {
//        if(isWorkoutValid(workout).not()) throw IncorrectFormException()
        workoutDao.insertWorkouts(listOf(workout))
    }

    suspend fun updateWorkout(workout: Workout) {
        //if(isUserValid(user).not()) throw IncorrectFormException()
        workoutDao.updateWorkout(workout)
    }

    suspend fun removeWorkout(workoutId: Long) {
        workoutDao.removeWorkoutById(workoutId)
    }

    suspend fun getAllWorkouts(): List<Workout> {
        return workoutDao.getAllWorkouts()
    }

    fun observeAllWorkouts(): Flow<List<Workout>> {
        return workoutDao.observeAllWorkouts()
    }
}