package com.axout.kts_android_09_2021.models

import java.util.*

data class SimpleItem(
    val author: String,
    val title: String,
    var votes: Int,
    val uuid: UUID
)