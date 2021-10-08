package com.axout.kts_android_09_2021.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.main.complex_list.ComplexDelegatesListAdapter
import com.axout.kts_android_09_2021.databinding.FragmentMainBinding
import com.axout.kts_android_09_2021.main.models.ComplexItem
import com.axout.kts_android_09_2021.main.models.LoadingItem
import com.axout.kts_android_09_2021.main.models.SimpleItem
import com.axout.kts_android_09_2021.utils.PaginationScrollListener
import com.axout.kts_android_09_2021.utils.autoCleared
import java.util.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private var complexAdapter: ComplexDelegatesListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        loadMoreItems()
    }

    private fun initList() {
        complexAdapter = ComplexDelegatesListAdapter()
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = complexAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            // Pagination
            addOnScrollListener(
                PaginationScrollListener(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 0
                )
            )

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun getDefaultItems() = List(20) {
        val randomUUID = UUID.randomUUID()
        when ((1..2).random()) {
            1 -> SimpleItem(
                author = getString(R.string.author),
                title = getString(R.string.simpleTitle),
                votes = 55,
                uuid = randomUUID
            )
            2 -> ComplexItem(
                author = getString(R.string.author),
                title = getString(R.string.complexTitle),
                uuid = randomUUID
            )
            else -> error("Wrong random number")
        }
    }

    private fun loadMoreItems() {
        val newItems = complexAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        } + getDefaultItems() + LoadingItem()
        complexAdapter.items = newItems
        Log.d("Pagination", newItems.size.toString())
    }
}