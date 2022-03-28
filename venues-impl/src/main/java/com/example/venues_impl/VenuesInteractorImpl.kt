package com.example.venues_impl

import android.util.Log
import com.example.common.location.GeoPoint
import com.example.common.util.Result
import com.example.venues.VenuesInteractor
import com.example.venues.VenuesState
import javax.inject.Inject

class VenuesInteractorImpl @Inject constructor(
    private val restaurantsNetworkDataSource: RestaurantsNetworkDataSource
) : VenuesInteractor {

    override suspend fun fetchVenues(location: GeoPoint): Result<VenuesState> =
        restaurantsNetworkDataSource.runCatching { getRestaurants(location.lat, location.lon) }
            .fold(
                onSuccess = { Result.Success(it) },
                onFailure = {
                    Log.e("VenuesInteractorImpl", "Venues data fetch failure", it)
                    Result.Failure(it)
                }
            )
}
