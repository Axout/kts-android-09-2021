package com.axout.kts_android_09_2021.detailed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.databinding.FragmentDetailedBinding
import com.axout.kts_android_09_2021.networking.Parameters
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class DetailedFragment : Fragment(R.layout.fragment_detailed) {

    private val viewModel: DetailedActivityViewModel by viewModels()

    private val binding by viewBinding(FragmentDetailedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("tag", "DetailedFragment.onViewCreated()")
        viewModel.getActivityById(id = Parameters.ACTIVITY_ID, include_all_efforts = true)
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.detailedActivity.observe(viewLifecycleOwner, Observer {
            binding.tvTitleComplex.text = it.name
            binding.tvAuthorComplex.text = it.distance.toString()

            //            coil, picasso
//            Glide.with()
//                .load(it.PhotosSummary.PhotosSummary_primary.urls)
//                .transform(CircleCrop())
//                .placeholder(R.drawable.route_2)
//                .into(binding.ivPhoto)
        })
    }
}