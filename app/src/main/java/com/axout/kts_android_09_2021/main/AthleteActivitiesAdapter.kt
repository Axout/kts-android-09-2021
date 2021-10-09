package com.axout.kts_android_09_2021.main

import androidx.recyclerview.widget.DiffUtil
import com.axout.kts_android_09_2021.networking.models.AthleteActivity
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AthleteActivitiesAdapter : AsyncListDifferDelegationAdapter<AthleteActivity>(
    AthleteActivitiesDiffUtilCallback()
) {

    init {
        delegatesManager.addDelegate(AthleteActivityAdapterDelegate())
    }

    class AthleteActivitiesDiffUtilCallback : DiffUtil.ItemCallback<AthleteActivity>() {
        override fun areItemsTheSame(oldItem: AthleteActivity, newItem: AthleteActivity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AthleteActivity, newItem: AthleteActivity): Boolean {
            return oldItem == newItem
        }
    }
}
