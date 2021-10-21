package com.axout.kts_android_09_2021.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.databinding.FragmentOnboardBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardFragment : Fragment(R.layout.fragment_onboard) {

    private val binding by viewBinding(FragmentOnboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val onboardAdapter = OnboardAdapter(activity as AppCompatActivity, resources.getStringArray(R.array.onboard_names).size)
        binding.onboardViewPager.adapter = onboardAdapter

        TabLayoutMediator(binding.tabLayout, binding.onboardViewPager) { tab, position ->
            //Some implementation
        }.attach()
    }
}