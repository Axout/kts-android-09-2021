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
        getDataForViewModel()
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.detailedActivity.observe(viewLifecycleOwner, Observer { detailedActivity ->
            binding.tvActivityName.text = detailedActivity.name
            binding.tvDistance.text = convertDistance(detailedActivity.distance)
            binding.tvTime.text = convertTime(detailedActivity.time)
            (detailedActivity.avgSpeed.toString() + " m/s").also { binding.tvAvgSpeed.text = it }
            (detailedActivity.maxSpeed.toString() + " m/s").also { binding.tvMaxSpeed.text = it }
            (detailedActivity.elevationGain.toString() + " m").also { binding.tvElevationGain.text = it }
            (detailedActivity.maxElevation.toString() + " m").also { binding.tvMaxElevation.text = it }

            val urlPhoto = detailedActivity.photos.primary?.urls?.bigPhoto

            Glide.with(this)
                .load(urlPhoto)
                .transform(CircleCrop())
                .placeholder(R.drawable.route_2)
                .into(binding.ivPhoto)
        })
    }

    private fun convertDistance(distance: Float): String {
        return String.format("%.2f km", distance / 1000)
    }

    private fun getDataForViewModel() {
        dataModel.activityID.value?.let { viewModel.getActivityById(id = it, include_all_efforts = true) }
    }

    private fun convertTime(totalSecs: Int): String {
        val hours = totalSecs / 3600
        val minutes = (totalSecs % 3600) / 60
        val seconds = totalSecs % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}