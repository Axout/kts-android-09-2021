package com.axout.kts_android_09_2021.detailed

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.data.presentation.DetailedWorkoutViewModel
import com.axout.kts_android_09_2021.databinding.FragmentDetailedBinding
import com.axout.kts_android_09_2021.main.models.DataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class DetailedFragment : Fragment(R.layout.fragment_detailed) {

    private val viewModel: DetailedActivityViewModel by viewModels()
    private val dataModel: DataModel by activityViewModels()
    private val args: DetailedFragmentArgs by navArgs()
    private val viewModelDetailedWorkout: DetailedWorkoutViewModel by viewModels()
    private val binding by viewBinding(FragmentDetailedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isOnline(context!!)) {
            bindViewModel()
        } else {
            bindViewModelDetailedWorkout()
        }
    }

    private fun bindViewModelDetailedWorkout() {

    }

    private fun bindViewModel() {

        viewModel.getActivityById(id = args.id, include_all_efforts = true)

        viewModel.detailedActivity.observe(viewLifecycleOwner, Observer { detailedActivity ->
            if (detailedActivity == null) {
                Toast.makeText(activity, R.string.not_connected, Toast.LENGTH_LONG).show()
            } else {
                (dataModel.firstname.value + " " + dataModel.lastname.value).also { binding.tvAuthor.text = it }
                binding.progress.isVisible = false
                binding.tvActivityName.text = detailedActivity.name
                binding.tvDistance.text = convertDistance(detailedActivity.distance)
                binding.tvTime.text = convertTime(detailedActivity.time)
                (detailedActivity.avgSpeed.toString() + " m/s").also { binding.tvAvgSpeed.text = it }
                (detailedActivity.maxSpeed.toString() + " m/s").also { binding.tvMaxSpeed.text = it }
                (detailedActivity.elevationGain.toString() + " m").also { binding.tvElevationGain.text = it }
                (detailedActivity.maxElevation.toString() + " m").also { binding.tvMaxElevation.text = it }

                Glide.with(this)
                    .load(dataModel.profile.value)
                    .transform(CircleCrop())
                    .placeholder(R.drawable.avatar_m)
                    .into(binding.ivAvatar)

                Glide.with(this)
                    .load(detailedActivity.photos.primary?.urls?.bigPhoto)
                    .transform(CircleCrop())
                    .placeholder(R.drawable.route_2)
                    .into(binding.ivPhoto)

                viewModelDetailedWorkout.save(args.id, detailedActivity)
            }
        })
    }

    private fun convertDistance(distance: Float): String {
        return String.format("%.2f km", distance / 1000)
    }

    private fun convertTime(totalSecs: Int): String {
        val hours = totalSecs / 3600
        val minutes = (totalSecs % 3600) / 60
        val seconds = totalSecs % 60
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val n = cm.activeNetwork
            if (n != null) {
                val nc = cm.getNetworkCapabilities(n)
                //проверяем wi-fi и мобильный интернет
                return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
            return false
        } else {
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}