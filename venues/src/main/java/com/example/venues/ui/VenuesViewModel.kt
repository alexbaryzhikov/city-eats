package com.example.venues.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.location.LocationProvider
import com.example.common.util.Result
import com.example.common.util.WhileViewSubscribed
import com.example.venues.VenuesInteractor
import com.example.venues.model.Venue
import com.example.venues.model.VenuesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MAX_ITEMS = 15

@HiltViewModel
class VenuesViewModel @Inject constructor(
    locationProvider: LocationProvider,
    private val venuesInteractor: VenuesInteractor
) : ViewModel() {

    val venuesState: StateFlow<VenuesState> = locationProvider.locations
        .mapLatest { venuesInteractor.fetchVenues(it) }
        .filterIsInstance<Result.Success<VenuesState>>()
        .map { it.data.copy(items = it.data.items.take(MAX_ITEMS)) }
        .stateIn(viewModelScope, WhileViewSubscribed, VenuesState())

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    init {
        venuesState.takeWhile { it.items.isEmpty() }
            .onCompletion { _loading.value = false }
            .launchIn(viewModelScope)
    }

    private val _favoriteIds: MutableStateFlow<Set<String>> = MutableStateFlow(emptySet())
    val favoriteIds: StateFlow<Set<String>> = _favoriteIds

    init {
        viewModelScope.launch {
            _favoriteIds.value = venuesInteractor.loadFavoriteIds()
        }
    }

    fun onVenueClicked(venue: Venue) {
        if (venue.id in _favoriteIds.value) {
            _favoriteIds.value -= setOf(venue.id)
        } else {
            _favoriteIds.value += setOf(venue.id)
        }
        viewModelScope.launch {
            venuesInteractor.saveFavoriteIds(_favoriteIds.value)
        }
    }
}
