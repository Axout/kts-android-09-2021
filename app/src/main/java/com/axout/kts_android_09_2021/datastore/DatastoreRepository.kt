package com.axout.kts_android_09_2021.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreRepository(
    context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun save(firstStart: Boolean) {
        dataStore.edit {
            it[KEY] = firstStart
        }
    }

    fun observe(): Flow<Boolean?> {
        return dataStore.data
            .map {
                it[KEY]
            }
    }

    companion object {
        private const val DATASTORE_NAME = "datastore"
        private val KEY = booleanPreferencesKey("key")
    }
}
