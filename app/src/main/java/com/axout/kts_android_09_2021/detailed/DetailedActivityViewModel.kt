package com.axout.kts_android_09_2021.detailed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axout.kts_android_09_2021.networking.DetailedActivityRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailedActivityViewModel: ViewModel() {

    private val repository = DetailedActivityRepository()
    private val detailedActivityLiveData = MutableLiveData<DetailedActivity>()

    private var currentGetDetailedActivityJob: Job? = null

    val detailedActivity: LiveData<DetailedActivity>
        get() = detailedActivityLiveData

    fun getActivityById(id: Long, include_all_efforts: Boolean) {
        currentGetDetailedActivityJob?.cancel()
        currentGetDetailedActivityJob = viewModelScope.launch {
            runCatching {
                Log.d("tag", "getActivityById()")
                repository.getActivityById(id, include_all_efforts)
            }.onSuccess {
                Log.d("tag", "onSuccess: $it")
                detailedActivityLiveData.postValue(it)
            }.onFailure {
                Log.d("tag", "onFailure: $it")
                detailedActivityLiveData.postValue(null)
            }
        }
    }
}