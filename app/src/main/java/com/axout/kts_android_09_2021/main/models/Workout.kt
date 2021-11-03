package com.axout.kts_android_09_2021.main.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Workout(
    val name: String,
    val id: Long,
    val distance: Float,
    @Json(name = "kudos_count")
    val kudos: Int
)