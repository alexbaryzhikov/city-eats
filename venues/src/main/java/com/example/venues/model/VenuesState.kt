package com.example.venues.model

data class VenuesState(
    val items: List<Venue> = emptyList()
)

data class Venue(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val iconUrl: String? = null
)
