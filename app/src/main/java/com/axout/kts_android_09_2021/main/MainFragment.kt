package com.axout.kts_android_09_2021.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.databinding.FragmentMainBinding
import com.axout.kts_android_09_2021.utils.autoCleared
import androidx.lifecycle.Observer
import com.axout.kts_android_09_2021.networking.Parameters

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: AthleteActivitiesViewModel by viewModels()

    private val binding by viewBinding(FragmentMainBinding::bind)
    private var athleteActivitiesAdapter: AthleteActivitiesAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        athleteActivitiesAdapter = AthleteActivitiesAdapter()
        binding.activitiesList.apply {
            val orientation = RecyclerView.VERTICAL
            adapter = athleteActivitiesAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        viewModel.athleteActivitiesList.observe(viewLifecycleOwner, Observer { athleteActivitiesAdapter.items = it })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { enableControls(it.not()) })
        viewModel.getListActivities(before = Parameters.BEFORE, after = Parameters.AFTER)
    }

    private fun enableControls(enable: Boolean) = with(binding) {

    }
}