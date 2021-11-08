package com.axout.kts_android_09_2021.splashscreen

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.authorize.AuthToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val sharedPreferences by lazy {
        requireContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000)
            selectNextFragment()
        }
    }

    private fun selectNextFragment() {
        val action =
            when (sharedPreferences.getInt(STATUS, 1)) {
                1 -> {
                    sharedPreferences.edit()
                        .putInt(STATUS, 2)
                        .apply()
                    SplashFragmentDirections.actionSplashFragmentToOnboardFragment()
                }
                2 -> SplashFragmentDirections.actionSplashFragmentToAuthFragment()
                3 -> {
                    AuthToken.token = sharedPreferences.getString(TOKEN, null)
                    SplashFragmentDirections.actionSplashFragmentToMainFragment()
                }
                else -> SplashFragmentDirections.actionSplashFragmentToOnboardFragment()
            }
        findNavController().navigate(action)
    }

    companion object {
        private const val SHARED_PREF_NAME = "shared_pref_name"
        private const val STATUS = "status"
        private const val TOKEN = "token"
    }
}