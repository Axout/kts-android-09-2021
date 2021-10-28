package com.axout.kts_android_09_2021

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import kotlinx.coroutines.flow.collect
import androidx.appcompat.app.AppCompatActivity
import com.axout.kts_android_09_2021.datastore.DatastoreViewModel
import com.axout.kts_android_09_2021.utils.launchOnStartedState

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<DatastoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.launchOnStartedState {
            viewModel.firstStartLiveData.collect { firstStart ->
                Log.d("tag", "firstStart = $firstStart")
                val navStartDestination =
                    when(firstStart) {
                        1 -> R.id.onboardFragment
                        2 -> R.id.authFragment
                        3 -> R.id.mainFragment
                        else -> R.id.onboardFragment
                    }
                Log.d("tag", "navStartDestination = $navStartDestination")
                //viewModel.save(2)
            }
        }
    }
}