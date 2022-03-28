package com.example.common.location

import kotlinx.coroutines.flow.SharedFlow

interface LocationProvider {
    val locations: SharedFlow<GeoPoint>
}
