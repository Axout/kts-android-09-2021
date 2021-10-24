package com.axout.kts_android_09_2021.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
import kotlinx.coroutines.flow.collect
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.databinding.FragmentMainBinding
import com.axout.kts_android_09_2021.utils.autoCleared
import androidx.lifecycle.Observer
import com.axout.kts_android_09_2021.networking.Parameters
import androidx.navigation.fragment.findNavController
import com.axout.kts_android_09_2021.data.models.Workout
import com.axout.kts_android_09_2021.data.presentation.WorkoutListAdapter
import com.axout.kts_android_09_2021.data.presentation.WorkoutListViewModel
import com.axout.kts_android_09_2021.main.models.DataModel
import com.axout.kts_android_09_2021.data.presentation.WorkoutViewModel
import com.axout.kts_android_09_2021.utils.launchOnStartedState

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: AthleteActivitiesViewModel by viewModels()
    private val viewModelAthlete: DetailedAthleteViewModel by viewModels()
    private val dataModel: DataModel by activityViewModels()
    private val viewModelWorkout by viewModels<WorkoutViewModel>()
    private val viewModelWorkoutList by viewModels<WorkoutListViewModel>()

    private val binding by viewBinding(FragmentMainBinding::bind)
    private var athleteActivitiesAdapter: ComplexDelegatesListAdapter by autoCleared()
    private var workoutAdapter: WorkoutListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isOnline(context!!)) {
            initList()
            bindViewModel()
        } else {
            initListWorkout()
            bindViewModelWorkout()
        }
    }

    private fun initListWorkout() {
        workoutAdapter = WorkoutListAdapter(::navigateToDetailedFragment)
        with(binding.activitiesList) {
            val orientation = RecyclerView.VERTICAL
            adapter = workoutAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun navigateToDetailedFragment(workout: Workout) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailedFragment(workout.id))
    }

    private fun initList() {
        athleteActivitiesAdapter = ComplexDelegatesListAdapter(
            detailedActivity = { athleteActivity ->
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailedFragment(athleteActivity.id))
            }
        )

        with(binding.activitiesList) {
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
                if (it.isEmpty()) {
                    Log.d("tag","list is empty")
                } else {
                    athleteActivitiesAdapter.items = it
                    for (item in it) {
                        viewModelWorkout.save(item.id, item.name, item.distance, item.kudos)
                    }
                }
            }
        })
        viewModel.getListActivities(before = Parameters.BEFORE, after = Parameters.AFTER)

        viewModelAthlete.getLoggedInAthlete()
        viewModelAthlete.detailedAthlete.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Toast.makeText(activity, R.string.not_connected, Toast.LENGTH_LONG).show()
            } else {
                dataModel.firstname.value = it.firstname
                dataModel.lastname.value = it.lastname
                dataModel.profile.value = it.profile
            }
        })
    }

    private fun bindViewModelWorkout() {
        viewLifecycleOwner.launchOnStartedState {
            viewModelWorkoutList.workoutsFlow.collect {
                workoutAdapter.items = it
            }
        }
    }

    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val n = cm.activeNetwork
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                //проверяем wi-fi и мобильный интернет
                return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
            return false
        } else {
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}