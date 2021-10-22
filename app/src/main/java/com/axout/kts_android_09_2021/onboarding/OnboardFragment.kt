package com.axout.kts_android_09_2021.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.databinding.FragmentOnboardBinding
import com.axout.kts_android_09_2021.datastore.DatastoreViewModel
import com.axout.kts_android_09_2021.utils.launchOnStartedState
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class OnboardFragment : Fragment(R.layout.fragment_onboard) {

    private val binding by viewBinding(FragmentOnboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("Hello timber!")

        val onboardAdapter = OnboardAdapter(activity as AppCompatActivity, resources.getStringArray(R.array.onboard_names).size)
        binding.onboardViewPager.adapter = onboardAdapter

        TabLayoutMediator(binding.tabLayout, binding.onboardViewPager) { tab, position ->
            //Some implementation
        }.attach()
    }
}