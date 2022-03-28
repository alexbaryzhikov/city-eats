package com.example.venues_impl

import com.example.common.location.GeoPoint
import com.example.common.util.Result
import com.example.venues.VenuesInteractor
import com.example.venues.VenueState
import javax.inject.Inject

class VenuesInteractorImpl @Inject constructor() : VenuesInteractor {

    override suspend fun fetchVenues(location: GeoPoint): Result<List<VenueState>> {
        data.shuffle()
        return Result.Success(data.toList())
    }

    companion object {
        private val data: MutableList<VenueState> = mutableListOf(
            VenueState("Burger Palace", "Beef + Bacon + Cheese = Love"),
            VenueState("Burger Haus Cafe", "Tasty homemade burgers!"),
            VenueState("Woolshed Bar & Kitchen", "Gourmet burgers"),
            VenueState("Maison Mumbai", "Famous Indian Cuisine"),
            VenueState("Dong Bei Hu", "Authentic Chinese Cuisine"),
            VenueState("O'Nam", "Finest Vietnamese"),
            VenueState("McDonald's Helsinki Kamppi", "I'm lovin' it."),
            VenueState("Hesburger Kallio", "Herkulliset hampurilaiset, tortillat ja salaatit"),
            VenueState("Sky Express", "Maukasta pizzaa"),
            VenueState("Fafa's Freda 47", "Herkulliset pitaleiv\u00e4t ja nyt t\u00e4ysin hiilineutraalina!"),
            VenueState("Taco Bell Tennispalatsi", "Meksikolainen pikaruokaravintola"),
            VenueState("Burger King Mannerheimintie", "Liekill\u00e4 grillatut hampurilaiset"),
            VenueState("Chilli Hakaniemi", "K\u00e4sintehty\u00e4 d\u00f6neri\u00e4"),
            VenueState("Subway Iso Roobertinkatu", "Parhaat hetket ovat edess\u00e4"),
            VenueState("Chill & Grill", "Pizza- ja kebabravintola"),
        )
    }
}
