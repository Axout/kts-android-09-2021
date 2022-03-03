package com.axout.kts_android_09_2021.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.databinding.FragmentProfileBinding
import com.axout.kts_android_09_2021.main.models.DataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val dataModel: DataModel by activityViewModels()
    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentPop())
        }
    }

    private fun bindViewModel() {
        val name = dataModel.firstname.value
        val lastname = dataModel.lastname.value
        if (name.isNullOrEmpty() || lastname.isNullOrEmpty()) {
            binding.tvAuthor.text = getString(R.string.author)
        } else {
            ("$name $lastname").also { binding.tvAuthor.text = it }
        }

        Glide.with(this)
            .load(dataModel.profile.value)
            .transform(CircleCrop())
            .placeholder(R.drawable.avatar_m)
            .into(binding.ivAvatar)
    }
}