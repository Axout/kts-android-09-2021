package com.axout.kts_android_09_2021.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.main.models.AthleteActivity
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_activities.view.*

class AthleteActivityAdapterDelegate(
    private val onItemClick: (item: AthleteActivity) -> Unit
) : AbsListItemAdapterDelegate<Any, Any, AthleteActivityAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is AthleteActivity
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activities, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as AthleteActivity)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: AthleteActivity) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: AthleteActivity? = null

        init {
            containerView.setOnClickListener { currentItem?.let(onItemClick) }
        }

        fun bind(item: AthleteActivity) = with(containerView) {
            currentItem = item
            "distance: ${item.distance} meters".also { tv_distance.text = it }
            tv_name.text = item.name
            tv_kudos.text = item.kudos.toString()
        }
    }
}
