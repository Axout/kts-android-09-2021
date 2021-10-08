package com.axout.kts_android_09_2021

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axout.kts_android_09_2021.login.ValidState

class CounterViewModel: ViewModel() {

    private val mutableState = MutableLiveData(ValidState(valid = false))

    val state: LiveData<ValidState>
        get() = mutableState

    fun validate(email: String, password: String) {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (isValidEmail && password.length >= 8) {
            mutableState.value = ValidState(true)
        } else {
            mutableState.value = ValidState(false)
        }
    }
}