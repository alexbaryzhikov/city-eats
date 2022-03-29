package com.example.cityeats.initializers

import android.content.Context
import androidx.startup.Initializer
import com.example.cityeats.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // Plant crashlytics here.
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
