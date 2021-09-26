package com.axout.kts_android_09_2021

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.databinding.FragmentMainBinding
import kotlin.random.Random

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btAddPaints.setOnClickListener {
            binding.frameLayout.setBackgroundColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
        }
    }
}