package com.example.venues_impl

import com.example.common.location.GeoPoint
import com.example.common.util.Result
import com.example.venues.VenuesInteractor
import com.example.venues.model.VenuesState
import com.example.venues_impl.api.RestaurantsNetworkDataSource
import com.example.venues_impl.data.VenuesDataStorage
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class VenuesInteractorImpl @Inject constructor(
    private val restaurantsNetworkDataSource: RestaurantsNetworkDataSource,
    private val venuesDataStorage: VenuesDataStorage
) : VenuesInteractor {

    override suspend fun fetchVenues(location: GeoPoint): Result<VenuesState> =
        restaurantsNetworkDataSource.runCatching { getRestaurants(location.lat, location.lon) }
            .fold(
                onSuccess = { Result.Success(it) },
                onFailure = {
                    Timber.w(it, "Venues data fetch failure")
                    Result.Failure(it)
                }
            )

    override suspend fun loadFavoriteIds(): Set<String> =
        venuesDataStorage.favoriteIds.first()

    override suspend fun saveFavoriteIds(ids: Set<String>) {
        venuesDataStorage.saveFavoriteIds(ids)
    }
}
