package com.example.cityeats.di

import com.example.common.location.LocationProvider
import com.example.common_impl.location.FakeLocationProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class CommonModule {

    @Singleton
    @Binds
    abstract fun bindLocationProvider(impl: FakeLocationProviderImpl): LocationProvider
}
