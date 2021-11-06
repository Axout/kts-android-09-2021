package com.axout.kts_android_09_2021.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class StartPointRepository(
    context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun save(status: Int) {
        dataStore.edit {
            it[KEY] = status
        }
    }

//    suspend fun saveToken(token: String) {
//        dataStore.edit {
//            it[TOKEN] = token
//        }
//    }

//    fun observe(): Flow<Int> {
//        return dataStore.data
//            .map {
//                it[KEY] ?: 1
//            }
//    }

    suspend fun read(): Int {
        val preferences = dataStore.data.first()
        return preferences[KEY] ?: 1
    }

//    suspend fun readToken(): String {
//        val preferences = dataStore.data.first()
//        return preferences[TOKEN].orEmpty()
//    }

    companion object {
        private const val DATASTORE_NAME = "datastore"
        private val KEY = intPreferencesKey("key")
//        private val TOKEN = stringPreferencesKey("token")
    }
}
