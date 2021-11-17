package com.axout.kts_android_09_2021.main

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.axout.kts_android_09_2021.main.models.Workout
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ComplexDelegatesListAdapter(
    detailedWorkout: (item: Workout) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(WorkoutAdapterDelegate(detailedWorkout))
            .addDelegate(PageLoadingAdapterDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is Workout -> first.id == (second as Workout).id
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }
}
