package com.axout.kts_android_09_2021.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.main.models.Workout
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_activities.view.*

class WorkoutAdapterDelegate(
    private val onItemClick: (item: Workout) -> Unit
) : AbsListItemAdapterDelegate<Any, Any, WorkoutAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is Workout
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activities, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as Workout)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: Workout) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: Workout? = null

        init {
            containerView.setOnClickListener { currentItem?.let(onItemClick) }
        }

        fun bind(item: Workout) = with(containerView) {
            currentItem = item
            "distance: ${item.distance} meters".also { tvDistance.text = it }
            tvName.text = item.name
            tvKudos.text = item.kudos.toString()
        }
    }
}
