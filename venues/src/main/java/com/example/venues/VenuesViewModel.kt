package com.example.venues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.location.LocationProvider
import com.example.common.util.Result
import com.example.common.util.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class VenuesViewModel @Inject constructor(
    locationProvider: LocationProvider,
    private val venuesInteractor: VenuesInteractor
) : ViewModel() {

    val venuesState: StateFlow<List<VenueState>> = locationProvider.locations
        .mapLatest { venuesInteractor.fetchVenues(it) }
        .filterIsInstance<Result.Success<List<VenueState>>>()
        .map { it.data }
        .stateIn(viewModelScope, WhileViewSubscribed, emptyList())
}
