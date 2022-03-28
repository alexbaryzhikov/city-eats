package com.example.venues_impl.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApi {

    @GET("v1/pages/restaurants")
    suspend fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): RestaurantsResponse

    @JsonClass(generateAdapter = true)
    data class RestaurantsResponse(
        @Json(name = "sections")
        val sections: List<SectionDto>
    )

    @JsonClass(generateAdapter = true)
    data class SectionDto(
        @Json(name = "items")
        val items: List<ItemDto>
    )

    @JsonClass(generateAdapter = true)
    data class ItemDto(
        @Json(name = "venue")
        val venue: VenueDto,

        @Json(name = "image")
        val image: ImageDto?
    )

    @JsonClass(generateAdapter = true)
    data class VenueDto(
        @Json(name = "id")
        val id: String,

        @Json(name = "name")
        val name: String,

        @Json(name = "short_description")
        val shortDescription: String?
    )

    @JsonClass(generateAdapter = true)
    data class ImageDto(
        @Json(name = "url")
        val url: String
    )
}