package com.example.cityeats

import com.example.venues.Venue
import com.example.venues.VenuesState

object VenuesTestData {
    val venuesState: VenuesState = VenuesState(
        items = listOf(
            Venue("11", "Burger Palace", "Beef + Bacon + Cheese = Love"),
            Venue("12", "Burger Haus Cafe", "Tasty homemade burgers!"),
            Venue("13", "Woolshed Bar & Kitchen", "Gourmet burgers"),
            Venue("14", "Maison Mumbai", "Famous Indian Cuisine"),
            Venue("15", "Dong Bei Hu", "Authentic Chinese Cuisine"),
            Venue("16", "O'Nam", "Finest Vietnamese"),
            Venue("17", "McDonald's Helsinki Kamppi", "I'm lovin' it."),
            Venue("18", "Hesburger Kallio", "Herkulliset hampurilaiset, tortillat ja salaatit"),
            Venue("19", "Sky Express", "Maukasta pizzaa"),
            Venue("20", "Fafa's Freda 47", "Herkulliset pitaleiv\u00e4t ja nyt t\u00e4ysin hiilineutraalina!"),
            Venue("21", "Taco Bell Tennispalatsi", "Meksikolainen pikaruokaravintola"),
            Venue("22", "Burger King Mannerheimintie", "Liekill\u00e4 grillatut hampurilaiset"),
            Venue("23", "Chilli Hakaniemi", "K\u00e4sintehty\u00e4 d\u00f6neri\u00e4"),
            Venue("24", "Subway Iso Roobertinkatu", "Parhaat hetket ovat edess\u00e4"),
            Venue("25", "Chill & Grill", "Pizza- ja kebabravintola"),
        )
    )
}