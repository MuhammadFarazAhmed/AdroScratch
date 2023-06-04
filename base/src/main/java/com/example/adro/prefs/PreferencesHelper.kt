package com.example.adro.prefs

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey


class PreferencesHelper  constructor()  {

    fun getSessionToken() = ""
    fun getCompany() = "ADO"
    fun getApiToken() = "k229rn-j#5W9-J8D#6-A6M0(o-!7#9&4\$x"
    fun getSRKey() = "!EyFde4#\$%gYsRct54fy@#\$5"

//    /* This returns us a flow of data from DataStore.
//  Basically as soon we update the value in Datastore,
//  the values returned by it also changes. */
//    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T):
//            Flow<T> = dataSource.data.catch { exception ->
//        if (exception is IOException) {
//            emit(emptyPreferences())
//        } else {
//            throw exception
//        }
//    }.map { preferences ->
//        val result = preferences[key] ?: defaultValue
//        result
//    }
//
//    /* This returns the last saved value of the key. If we change the value,
//        it wont effect the values produced by this function */
//    override suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T):
//            T = dataSource.data.first()[key] ?: defaultValue
//
//    // This Sets the value based on the value passed in value parameter.
//    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
//        dataSource.edit { preferences ->
//            preferences[key] = value
//        }
//    }
//
//    // This Function removes the Key Value pair from the datastore, hereby removing it completely.
//    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
//        dataSource.edit { preferences ->
//            preferences.remove(key)
//        }
//    }
//
//    // This function clears the entire Preference Datastore.
//    override suspend fun clearAllPreference() {
//        dataSource.edit { preferences ->
//            preferences.clear()
//        }
//    }

}

object PreferenceDataStoreConstants {
    val IS_MINOR_KEY = booleanPreferencesKey("IS_MINOR_KEY")
    val AGE_KEY = intPreferencesKey("AGE_KEY")
    val NAME_KEY = stringPreferencesKey("NAME_KEY")
    val MOBILE_NUMBER = longPreferencesKey("MOBILE_NUMBER")
}