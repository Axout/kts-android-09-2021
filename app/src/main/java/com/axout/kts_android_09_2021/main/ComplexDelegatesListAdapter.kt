package com.axout.kts_android_09_2021.main

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.axout.kts_android_09_2021.main.models.AthleteActivity
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ComplexDelegatesListAdapter(
    detailedActivity: (item: AthleteActivity) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(AthleteActivityAdapterDelegate(detailedActivity))
            .addDelegate(PageLoadingAdapterDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is AthleteActivity -> first.id == (second as AthleteActivity).id
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }
}
