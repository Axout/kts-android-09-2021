package com.axout.kts_android_09_2021

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.axout.kts_android_09_2021.data.db.Database
import timber.log.Timber

class StrunnerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Database.init(this)
    }

}