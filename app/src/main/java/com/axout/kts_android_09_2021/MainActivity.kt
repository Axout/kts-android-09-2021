package com.axout.kts_android_09_2021

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.axout.kts_android_09_2021.datastore.DatastoreViewModel
import com.axout.kts_android_09_2021.utils.launchOnStartedState

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<DatastoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.launchOnStartedState {
            viewModel.firstStartLiveData
                .take(1)
                .collect { status ->
                    Log.d("tag", "firstStart = $status")
                    val navStartDestination =
                        when (status) {
                            1 -> {
                                viewModel.save(2)
                                R.id.onboardFragment
                            }
                            2 -> R.id.authFragment
                            3 -> R.id.mainFragment
                            else -> R.id.onboardFragment
                        }
                    setNavStartDestination(navStartDestination)
                }
        }
    }

    private fun setNavStartDestination(navStartDestination: Int) {
        Log.d("tag", "navStartDestination = $navStartDestination")
    }
}