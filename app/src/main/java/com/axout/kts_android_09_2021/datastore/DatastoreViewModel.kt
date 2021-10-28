package com.axout.kts_android_09_2021.datastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DatastoreViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = DatastoreRepository(application)

    val firstStartLiveData: Flow<Int>
        get() = repository.observe()

    fun save(firstStart: Int) {
        viewModelScope.launch {
            repository.save(firstStart)
        }
    }
}