package com.example.venues

import com.example.common.location.GeoPoint
import com.example.common.util.Result
import com.example.venues.model.VenuesState

interface VenuesInteractor {
    suspend fun fetchVenues(location: GeoPoint): Result<VenuesState>
    suspend fun loadFavoriteIds(): Set<String>
    suspend fun saveFavoriteIds(ids: Set<String>)
}
