package com.axout.kts_android_09_2021.detailed

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailedActivity(
    val name: String,
    val distance: Float,
) {
    inner class PhotosSummary(
        val count: Int
    ) {
        inner class PhotosSummary_primary(
            val urls: String
        )
    }
}
