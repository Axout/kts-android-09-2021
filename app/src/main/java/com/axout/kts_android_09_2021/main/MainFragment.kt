package com.axout.kts_android_09_2021.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
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
import androidx.navigation.fragment.findNavController
import com.axout.kts_android_09_2021.main.models.DataModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: AthleteActivitiesViewModel by viewModels()
    private val viewModelAthlete: DetailedAthleteViewModel by viewModels()
    private val dataModel: DataModel by activityViewModels()

    private val binding by viewBinding(FragmentMainBinding::bind)
    private var athleteActivitiesAdapter: ComplexDelegatesListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        athleteActivitiesAdapter = ComplexDelegatesListAdapter(
            detailedActivity = { athleteActivity ->
                dataModel.activityID.value = athleteActivity.id
                Log.d("tag", "activity id = ${athleteActivity.id}")
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailedFragment())
            }
        )

        binding.activitiesList.apply {
            val orientation = RecyclerView.VERTICAL
            adapter = athleteActivitiesAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        viewModel.athleteActivitiesList.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Toast.makeText(activity, R.string.no_activities, Toast.LENGTH_LONG).show()
            } else {
                athleteActivitiesAdapter.items = it
            }
        })
        viewModel.getListActivities(before = Parameters.BEFORE, after = Parameters.AFTER)

        viewModelAthlete.getLoggedInAthlete()
        viewModelAthlete.detailedAthlete.observe(viewLifecycleOwner, Observer {
            dataModel.firstname.value = it.firstname
            dataModel.lastname.value = it.lastname
            dataModel.profile.value = it.profile
        })
    }
}