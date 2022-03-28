package com.example.venues_impl

import com.example.venues.Venue
import com.example.venues.VenuesState
import javax.inject.Inject

class RestaurantsNetworkDataSource @Inject constructor(
    private val restaurantsApi: RestaurantsApi
) {

    suspend fun getRestaurants(lat: Double, lon: Double): VenuesState {
        val response = restaurantsApi.getRestaurants(lat, lon)
        return response.toVenues()
    }

    private fun RestaurantsApi.RestaurantsResponse.toVenues(): VenuesState = VenuesState(
        items = sections.flatMap { section ->
            section.items.map { item ->
                Venue(
                    id = item.venue.id,
                    title = item.venue.name,
                    subtitle = item.venue.shortDescription,
                    iconUrl = item.image?.url
                )
            }
        }
    )
}