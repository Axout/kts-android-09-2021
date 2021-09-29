package com.axout.kts_android_09_2021.complex_list

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.axout.kts_android_09_2021.models.ComplexItem
import com.axout.kts_android_09_2021.models.SimpleItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ComplexDelegatesListAdapter : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(SimpleItemDelegate(::removeItem))
            .addDelegate(ComplexItemDelegate())
            .addDelegate(PageLoadingAdapterDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is SimpleItem -> first.uuid == (second as SimpleItem).uuid
                is ComplexItem -> first.uuid == (second as ComplexItem).uuid
//                is ImageItem -> first.id == (second as ImageItem).id
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }

    private fun removeItem(item: Any) {
        val newItems = items.toMutableList().apply {
            remove(item)
        }
        items = newItems
    }
}
