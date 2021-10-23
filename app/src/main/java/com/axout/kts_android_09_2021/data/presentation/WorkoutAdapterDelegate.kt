package com.axout.kts_android_09_2021.data.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axout.kts_android_09_2021.data.models.Workout
import com.axout.kts_android_09_2021.databinding.ItemActivitiesBinding
import com.axout.kts_android_09_2021.utils.bindingInflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class WorkoutAdapterDelegate(
    private val onWorkoutClick: (Workout) -> Unit
) : AbsListItemAdapterDelegate<Workout, Workout, WorkoutAdapterDelegate.Holder>() {

    override fun isForViewType(item: Workout, items: MutableList<Workout>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return parent.bindingInflate(ItemActivitiesBinding::inflate).let {
            Holder(it, onWorkoutClick)
        }
    }

    override fun onBindViewHolder(item: Workout, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
        private val binding: ItemActivitiesBinding,
        onWorkoutClick: (Workout) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentWorkout: Workout? = null

        init {
            binding.root.setOnClickListener { currentWorkout?.let(onWorkoutClick) }
        }

        fun bind(item: Workout) {
            currentWorkout = item
            "distance: ${item.distance} meters".also { binding.tvDistance.text = it }
            binding.tvName.text = item.name
            binding.tvKudos.text = item.kudos.toString()
        }
    }

}
