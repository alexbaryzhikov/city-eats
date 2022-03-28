package com.example.cityeats.di

import com.example.venues.VenuesInteractor
import com.example.venues_impl.VenuesInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class VenuesModule {

    @Singleton
    @Binds
    abstract fun bindVenuesInteractor(impl: VenuesInteractorImpl): VenuesInteractor
}
