package com.example.kts_android_09_2021

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {

    private val mutableState = MutableLiveData(CounterState(counter = 0))

    val state: LiveData<CounterState>
        get() = mutableState

    fun setData(data: Int) {
        mutableState.value = CounterState(data)
    }
}