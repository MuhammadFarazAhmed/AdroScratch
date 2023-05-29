package com.example.adro.common

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface Storage<T> {

    fun insert(key: Preferences.Key<T>, data: T): Flow<Int>

    fun insert(data: List<T>): Flow<Int>

    fun get(where: (T) -> Boolean): Flow<T>

    fun getAll(): Flow<List<T>>

    fun clearAll(): Flow<Int>
}