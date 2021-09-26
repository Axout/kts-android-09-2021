package com.axout.kts_android_09_2021

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.databinding.FragmentOnboardBinding

class OnboardFragment : Fragment(R.layout.fragment_onboard) {

    private val binding by viewBinding(FragmentOnboardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btOnLoginFrag.setOnClickListener {
            val action = OnboardFragmentDirections.actionOnboardFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
}