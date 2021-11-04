package com.axout.kts_android_09_2021.datastore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StartPointViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = StartPointRepository(application)

    val firstStartLiveData: Flow<Int>
        get() = repository.observe()

    fun save(status: Int) {
        viewModelScope.launch {
            repository.save(status)
        }
    }

    suspend fun read(): Int {
        return repository.read()
    }
}