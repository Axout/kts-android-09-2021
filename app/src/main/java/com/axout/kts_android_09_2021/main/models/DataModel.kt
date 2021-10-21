package com.axout.kts_android_09_2021.main.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {

    /*
    "by lazy" говорит, что при создании класса DataModel впервые создаётся то,
    что находится после фигурных скобок.
    При повторном создании класса DataModel будет использоваться то,
    что уже было создано после фигурных скобок.
     */
    val activityID: MutableLiveData<Long> by lazy { MutableLiveData<Long>() }
    val firstname: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val lastname: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val profile: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}