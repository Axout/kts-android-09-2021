package com.axout.kts_android_09_2021

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.axout.kts_android_09_2021.datastore.DatastoreViewModel
import kotlinx.coroutines.flow.collect
import com.axout.kts_android_09_2021.utils.launchOnStartedState

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var navController: NavController
    lateinit var navHost: NavHostFragment
    private val viewModel by viewModels<DatastoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController
        this.launchOnStartedState {
            viewModel.firstStartLiveData.collect {
                setStartPoint(it)
            }
        }
        viewModel.save(true)
    }

    private fun setStartPoint(firstStart: Boolean) {
        val graph =
            if (firstStart) {
                navController.navInflater.inflate(R.navigation.nav_graph).also {
                    it.setStartDestination(R.id.authFragment)
                }
            } else {
                navController.navInflater.inflate(R.navigation.nav_graph).also {
                    it.setStartDestination(R.id.onboardFragment)
                }
            }
        navController.setGraph(graph, null)
    }
}