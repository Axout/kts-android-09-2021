package com.axout.kts_android_09_2021.splashscreen

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.datastore.StartPointViewModel
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    lateinit var handler: Handler
    private val viewModel by viewModels<StartPointViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = Handler()
        handler.postDelayed({
            selectNextFragment()
        }, 3000)
    }

    private fun selectNextFragment() {
        lifecycleScope.launch {
            val status = viewModel.read()
            Log.d("tag", "status = $status")
            val action =
                when(status) {
                    1 -> {
                        viewModel.save(2)
                        SplashFragmentDirections.actionSplashFragmentToOnboardFragment()
                    }
                    2 -> SplashFragmentDirections.actionSplashFragmentToAuthFragment()
                    3 -> SplashFragmentDirections.actionSplashFragmentToMainFragment()
                    else -> SplashFragmentDirections.actionSplashFragmentToOnboardFragment()
                }
            findNavController().navigate(action)
        }
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }
}