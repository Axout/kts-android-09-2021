package com.axout.kts_android_09_2021.detailed

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.R
import com.axout.kts_android_09_2021.databinding.FragmentDetailedBinding
import com.axout.kts_android_09_2021.main.models.DataModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import kotlinx.android.synthetic.main.fragment_main.view.topAppBar

class DetailedFragment : Fragment(R.layout.fragment_detailed) {

    private val viewModel: DetailedWorkoutViewModel by viewModels()
//    private val dataModel: DataModel by activityViewModels()
    private val args: DetailedFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentDetailedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(DetailedFragmentDirections.actionDetailedFragmentPop())
        }
    }

    private fun bindViewModel() {

        viewModel.getActivityById(online = isOnline(context!!), id = args.id)

        viewModel.detailedWorkout.observe(viewLifecycleOwner, Observer { detailedWorkout ->
            try {
                //(dataModel.firstname.value + " " + dataModel.lastname.value).also { binding.tvAuthor.text = it }
                binding.progress.isVisible = false
                binding.appBar.topAppBar.title = detailedWorkout.name
                binding.tvDistance.text = convertDistance(detailedWorkout.distance)
                binding.tvTime.text = convertTime(detailedWorkout.time)
                (detailedWorkout.avgSpeed.toString() + " m/s").also { binding.tvAvgSpeed.text = it }
                (detailedWorkout.maxSpeed.toString() + " m/s").also { binding.tvMaxSpeed.text = it }
                (detailedWorkout.elevationGain.toString() + " m").also { binding.tvElevationGain.text = it }
                (detailedWorkout.maxElevation.toString() + " m").also { binding.tvMaxElevation.text = it }

    //                Glide.with(this)
    //                    .load(dataModel.profile.value)
    //                    .transform(CircleCrop())
    //                    .placeholder(R.drawable.avatar_m)
    //                    .into(binding.ivAvatar)

                if (detailedWorkout.photo != null) {
                    Glide.with(this)
                        .load(detailedWorkout.photo)
                        .transform(CircleCrop())
                        .placeholder(R.drawable.route_back)
                        .into(binding.ivPhoto)
                } else {
                    Glide.with(this)
                        .load(R.drawable.route_2)
                        .placeholder(R.drawable.route_back)
                        .into(binding.ivPhoto)
                }

            } catch (t: Throwable) {
                Toast.makeText(context, getString(R.string.not_workout), Toast.LENGTH_SHORT).show()
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