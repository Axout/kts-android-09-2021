package com.axout.kts_android_09_2021

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.axout.kts_android_09_2021.datastore.DatastoreViewModel
import com.axout.kts_android_09_2021.utils.launchOnStartedState

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<DatastoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectStartFragment()
//        setNavStartFragment(R.id.authFragment)

//        this.launchOnStartedState {
//            viewModel.startStatusLiveData
//                .take(1)
//                .collect { status ->
//                Log.d("tag", "status = $status")
//                val navStartDestination =
//                    when(status) {
//                        1 -> {
//                            viewModel.save(2)
//                            R.id.onboardFragment
//                        }
//                        2 -> R.id.authFragment
//                        3 -> R.id.mainFragment
//                        else -> R.id.onboardFragment
//                    }
//                    setNavStartFragment(navStartDestination)
//            }
//        }
    }

    private fun selectStartFragment() {
        this.launchOnStartedState {
            val status = viewModel.read()
            Log.d("tag", "status = $status")
            val navStartDestination =
                when(status) {
                    1 -> {
                        viewModel.save(2)
                        R.id.onboardFragment
                    }
                    2 -> R.id.authFragment
                    3 -> R.id.mainFragment
                    else -> R.id.onboardFragment
                }
            setNavStartFragment(navStartDestination)
        }
    }

    private fun setNavStartFragment(navStartDestination: Int) {
        Log.d("tag", "navStartDestination = $navStartDestination")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navGraph.setStartDestination(navStartDestination)
        navController.graph = navGraph
    }
}