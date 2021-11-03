package com.axout.kts_android_09_2021.main.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Athlete (
    val id: Long,
    val firstname: String?,
    val lastname: String?,
    val profile_medium: String?,
    val profile: String?
)
