package com.axout.kts_android_09_2021.main.complex_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.main.models.ComplexItem
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_complex.view.*

class ComplexItemDelegate : AbsListItemAdapterDelegate<Any, Any, ComplexItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is ComplexItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_complex, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as ComplexItem)
    }

    inner class ViewHolder(
        override val containerView: View,
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: ComplexItem? = null

        fun bind(item: ComplexItem) = with(containerView) {
            currentItem = item
            tv_authorComplex.text = item.author
            tv_titleComplex.text = item.title
        }
    }
}
