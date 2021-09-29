package com.axout.kts_android_09_2021.complex_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.models.SimpleItem
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_simple.view.*

class SimpleItemDelegate(
    private val onItemClick: (item: SimpleItem) -> Unit
) : AbsListItemAdapterDelegate<Any, Any, SimpleItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is SimpleItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_simple, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as SimpleItem)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: SimpleItem) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: SimpleItem? = null

        init {
            containerView.setOnClickListener { currentItem?.let(onItemClick) }
        }

        fun bind(item: SimpleItem) = with(containerView) {
            currentItem = item
            tv_author.text = item.author
            tv_title.text = item.title
            tv_votes.text = item.votes.toString()
        }
    }
}