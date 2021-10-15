package com.axout.kts_android_09_2021.detailed

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailedActivity(
    val name: String,
    val distance: Float,
    @Json(name = "moving_time")
    val time: Int,
    @Json(name = "average_speed")
    val avgSpeed: Float,
    @Json(name = "max_speed")
    val maxSpeed: Float,
    @Json(name = "total_elevation_gain")
    val elevationGain: Float,
    @Json(name = "elev_high")
    val maxElevation: Float,
    val photos: PhotosSummary
)

@JsonClass(generateAdapter = true)
data class PhotosSummary(
    val count: Int,
    val primary: PhotosSummary_primary?
)

@JsonClass(generateAdapter = true)
data class PhotosSummary_primary(
    val urls: Urls?
)

@JsonClass(generateAdapter = true)
data class Urls(
    @Json(name = "100")
    val smallPhoto: String,
    @Json(name = "600")
    val bigPhoto: String
)