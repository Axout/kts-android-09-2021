package com.axout.kts_android_09_2021

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axout.kts_android_09_2021.databinding.FragmentOnboardBinding

class OnboardFragment : Fragment() {

    private lateinit var binding: FragmentOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btOnLoginFrag.setOnClickListener {
            val action = OnboardFragmentDirections.actionOnboardFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
}