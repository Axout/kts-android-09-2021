package com.axout.kts_android_09_2021.data.db

import android.content.Context
import androidx.room.Room

object Database {

    lateinit var instance: ApplicationDatabase
        private set

    fun init(context: Context) {
        instance = Room.databaseBuilder(
            context,
            ApplicationDatabase::class.java,
            ApplicationDatabase.DB_NAME
        )
            .build()
    }
}