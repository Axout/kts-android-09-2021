package com.axout.kts_android_09_2021.splashscreen

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.axout.kts_android_09_2021.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR

        handler = Handler()
        handler.postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToOnboardFragment()
            findNavController().navigate(action)
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }, 3000)
    }
}