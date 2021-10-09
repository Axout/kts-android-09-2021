package com.axout.kts_android_09_2021.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axout.kts_android_09_2021.databinding.ItemActivitiesBinding
import com.axout.kts_android_09_2021.networking.models.AthleteActivity
import com.axout.kts_android_09_2021.utils.bindingInflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class AthleteActivityAdapterDelegate :
    AbsListItemAdapterDelegate<AthleteActivity, AthleteActivity, AthleteActivityAdapterDelegate.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.bindingInflate(ItemActivitiesBinding::inflate))
    }

    override fun isForViewType(
        item: AthleteActivity,
        items: MutableList<AthleteActivity>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onBindViewHolder(item: AthleteActivity, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
        private val binding: ItemActivitiesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AthleteActivity) {
            "distance: ${item.distance} meters".also { binding.tvDistance.text = it }
            binding.tvName.text = item.name
            binding.tvKudos.text = item.kudos.toString()
        }
    }
}
