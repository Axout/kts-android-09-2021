package com.axout.kts_android_09_2021.data.presentation

import androidx.recyclerview.widget.DiffUtil
import com.axout.kts_android_09_2021.data.models.LocalWorkout
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class WorkoutListAdapter(
    onWorkoutClick: (LocalWorkout) -> Unit
) : AsyncListDifferDelegationAdapter<LocalWorkout>(WorkoutDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(WorkoutAdapterDelegate(onWorkoutClick))
    }

    class WorkoutDiffUtilCallback : DiffUtil.ItemCallback<LocalWorkout>() {
        override fun areItemsTheSame(oldItem: LocalWorkout, newItem: LocalWorkout): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocalWorkout, newItem: LocalWorkout): Boolean {
            return oldItem == newItem
        }
    }

}
