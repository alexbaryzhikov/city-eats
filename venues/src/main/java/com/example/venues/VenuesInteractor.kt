package com.example.venues

import com.example.common.location.GeoPoint
import com.example.common.util.Result

interface VenuesInteractor {
    suspend fun fetchVenues(location: GeoPoint): Result<VenuesState>
}
