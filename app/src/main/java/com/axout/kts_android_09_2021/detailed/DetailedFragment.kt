package com.axout.kts_android_09_2021.detailed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.databinding.FragmentDetailedBinding
import com.axout.kts_android_09_2021.main.models.DataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class DetailedFragment : Fragment(R.layout.fragment_detailed) {

    private val viewModel: DetailedActivityViewModel by viewModels()
    private val dataModel: DataModel by activityViewModels()

    private val binding by viewBinding(FragmentDetailedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("tag", "activityID = ${dataModel.activityID.value}")
        dataModel.activityID.value?.let { viewModel.getActivityById(id = it, include_all_efforts = true) }
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.detailedActivity.observe(viewLifecycleOwner, Observer {
            binding.tvActivityName.text = it.name
            binding.tvDistance.text = it.distance.toString() + " m"
            binding.tvTime.text = it.time.toString() + " s"
            binding.tvAvgSpeed.text = it.avgSpeed.toString() + " m/s"
            binding.tvMaxSpeed.text = it.maxSpeed.toString() + " m/s"
            binding.tvElevationGain.text = it.elevationGain.toString() + " m"
            binding.tvMaxElevation.text = it.maxElevation.toString() + " m"

            Glide.with(this)
                .load("https://dgtzuqphqg23d.cloudfront.net/suacqRl4BjBgq69pBkQvt5_qcYLee9KLMrC-7eZRbwQ-96x128.jpg")
                .transform(CircleCrop())
                .placeholder(R.drawable.route_2)
                .into(binding.ivPhoto)
        })
    }
}