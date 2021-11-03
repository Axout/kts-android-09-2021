package com.axout.kts_android_09_2021.data.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axout.kts_android_09_2021.data.models.LocalWorkout
import com.axout.kts_android_09_2021.databinding.ItemActivitiesBinding
import com.axout.kts_android_09_2021.utils.bindingInflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class LocalWorkoutAdapterDelegate(
    private val onWorkoutClick: (LocalWorkout) -> Unit
) : AbsListItemAdapterDelegate<LocalWorkout, LocalWorkout, LocalWorkoutAdapterDelegate.Holder>() {

    override fun isForViewType(item: LocalWorkout, items: MutableList<LocalWorkout>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return parent.bindingInflate(ItemActivitiesBinding::inflate).let {
            Holder(it, onWorkoutClick)
        }
    }

    override fun onBindViewHolder(item: LocalWorkout, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
        private val binding: ItemActivitiesBinding,
        onWorkoutClick: (LocalWorkout) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentLocalWorkout: LocalWorkout? = null

        init {
            binding.root.setOnClickListener { currentLocalWorkout?.let(onWorkoutClick) }
        }

        fun bind(item: LocalWorkout) {
            currentLocalWorkout = item
            "distance: ${item.distance} meters".also { binding.tvDistance.text = it }
            binding.tvName.text = item.name
            binding.tvKudos.text = item.kudos.toString()
        }
    }

}
