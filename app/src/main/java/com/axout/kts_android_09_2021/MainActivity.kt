package com.axout.kts_android_09_2021

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import kotlinx.coroutines.flow.collect
import androidx.appcompat.app.AppCompatActivity
import com.axout.kts_android_09_2021.datastore.DatastoreViewModel
import com.axout.kts_android_09_2021.utils.launchOnStartedState

class MainActivity : AppCompatActivity(R.layout.activity_main) {
//
//    private val viewModel by viewModels<DatastoreViewModel>()
//    private var firstStart: Boolean? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        this.launchOnStartedState {
//            viewModel.firstStartLiveData.collect {
//                Log.d("tag", "it = $it")
//                firstStart = it == null
//            }
//        }
//        viewModel.save(false)
//        Log.d("tag", "firstStart = $firstStart")
//    }
}