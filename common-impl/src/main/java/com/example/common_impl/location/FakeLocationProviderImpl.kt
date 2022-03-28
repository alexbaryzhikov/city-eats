package com.example.common_impl.location

import com.example.common.di.ApplicationScope
import com.example.common.location.GeoPoint
import com.example.common.location.LocationProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FakeLocationProviderImpl @Inject constructor(
    @ApplicationScope scope: CoroutineScope
) : LocationProvider {

    var updateIntervalMillis = TimeUnit.SECONDS.toMillis(10)

    var userLocations = listOf(
        GeoPoint(60.170187, 24.930599),
        GeoPoint(60.169418, 24.931618),
        GeoPoint(60.169818, 24.932906),
        GeoPoint(60.170005, 24.935105),
        GeoPoint(60.169108, 24.936210),
        GeoPoint(60.168355, 24.934869),
        GeoPoint(60.167560, 24.932562),
        GeoPoint(60.168254, 24.931532),
        GeoPoint(60.169012, 24.930341),
        GeoPoint(60.170085, 24.929569),
    )

    override val locations: SharedFlow<GeoPoint> = flow {
        var i = 0
        while (true) {
            emit(userLocations[i])
            i = (i + 1) % userLocations.size
            delay(updateIntervalMillis)
        }
    }.shareIn(scope, SharingStarted.WhileSubscribed(), 1)
}
