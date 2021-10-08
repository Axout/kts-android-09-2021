package com.axout.kts_android_09_2021.main.complex_list

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.axout.kts_android_09_2021.main.models.ComplexItem
import com.axout.kts_android_09_2021.main.models.SimpleItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ComplexDelegatesListAdapter : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(SimpleItemDelegate(::setItem))
            .addDelegate(ComplexItemDelegate())
            .addDelegate(PageLoadingAdapterDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is SimpleItem -> first.uuid == (second as SimpleItem).uuid
                is ComplexItem -> first.uuid == (second as ComplexItem).uuid
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }

    private fun setItem(item: Any) {
        val newItems = items.toMutableList().apply {
            val position = indexOf(item)
            Log.d("like", "position = $position")
            set(position, item)
            notifyItemChanged(position)
        }
        items = newItems
    }
}
