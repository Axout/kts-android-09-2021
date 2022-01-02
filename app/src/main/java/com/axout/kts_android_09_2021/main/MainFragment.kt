package com.axout.kts_android_09_2021.main

import android.content.Context
import android.content.pm.ActivityInfo
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
import androidx.navigation.fragment.findNavController
import com.axout.kts_android_09_2021.data.models.LocalWorkout
import com.axout.kts_android_09_2021.data.presentation.LocalWorkoutListAdapter
import com.axout.kts_android_09_2021.data.presentation.LocalWorkoutListViewModel
import com.axout.kts_android_09_2021.main.models.DataModel
import com.axout.kts_android_09_2021.data.presentation.LocalWorkoutViewModel
import com.axout.kts_android_09_2021.utils.launchOnStartedState

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModelWorkouts: WorkoutsViewModel by viewModels()
    private val viewModelAthlete: AthleteViewModel by viewModels()
    private val viewModelLocalWorkout: LocalWorkoutViewModel by viewModels()
    private val viewModelWorkoutList: LocalWorkoutListViewModel by viewModels()
    private val dataModel: DataModel by activityViewModels()

    private val binding by viewBinding(FragmentMainBinding::bind)
    private var workoutsAdapter: ComplexDelegatesListAdapter by autoCleared()
    private var localWorkoutAdapter: LocalWorkoutListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR

        if (isOnline(context!!)) {
            initList()
            bindViewModel()
        } else {
            initListWorkout()
            bindViewModelWorkout()
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.profile -> {
                    findNavController().navigate(MainFragmentDirections.actionMainFragmentToProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun initListWorkout() {
        localWorkoutAdapter = LocalWorkoutListAdapter(::navigateToDetailedFragment)
        with(binding.activitiesList) {
            val orientation = RecyclerView.VERTICAL
            adapter = localWorkoutAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun navigateToDetailedFragment(localWorkout: LocalWorkout) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailedFragment(localWorkout.id))
    }

    private fun initList() {
        workoutsAdapter = ComplexDelegatesListAdapter(
            detailedWorkout = { workout ->
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailedFragment(workout.id))
            }
        )

        with(binding.activitiesList) {
            val orientation = RecyclerView.VERTICAL
            adapter = workoutsAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        viewModelWorkouts.workoutsList.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Toast.makeText(activity, R.string.no_activities, Toast.LENGTH_SHORT).show()
            } else {
                if (it.isEmpty()) {
                    Log.d("tag","list is empty")
                } else {
                    workoutsAdapter.items = it
                    for (item in it) {
                        viewModelLocalWorkout.save(item.id, item.name, item.distance, item.kudos)
                    }
                }
            }
        })
        viewModelWorkouts.getListWorkouts(before = (System.currentTimeMillis() / 1000).toInt(), after = 0)

        viewModelAthlete.getLoggedInAthlete()
        viewModelAthlete.athlete.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Toast.makeText(activity, R.string.not_connected, Toast.LENGTH_SHORT).show()
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
                localWorkoutAdapter.items = it.reversed()
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