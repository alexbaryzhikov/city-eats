package com.example.venues_impl.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VenuesDataStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val favoriteIds: Flow<Set<String>> = dataStore.data.map {
        it[FAVORITE_IDS_KEY] ?: emptySet()
    }

    suspend fun saveFavoriteIds(ids: Set<String>) {
        dataStore.edit { it[FAVORITE_IDS_KEY] = ids }
    }

    companion object {
        const val PREFS_NAME = "city_eats.venues"

        private val FAVORITE_IDS_KEY = stringSetPreferencesKey("favorite_ids")
    }
}