package com.axout.kts_android_09_2021

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axout.kts_android_09_2021.databinding.FragmentMainBinding
import kotlin.random.Random

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btAddPaints.setOnClickListener {
            binding.frameLayout.setBackgroundColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
        }
    }
}