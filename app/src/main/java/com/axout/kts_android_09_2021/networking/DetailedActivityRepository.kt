package com.axout.kts_android_09_2021.networking

import com.axout.kts_android_09_2021.detailed.DetailedActivity

class DetailedActivityRepository {

    suspend fun getActivityById(
        id: Long,
        include_all_efforts: Boolean
    ): DetailedActivity {
        return Networking.stravaApi.getActivityById(id, include_all_efforts)
    }
}