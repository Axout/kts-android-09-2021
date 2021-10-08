package com.axout.kts_android_09_2021.networking.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AthleteActivity(
    val name: String,
    val id: Long,
    val distance: Float
)