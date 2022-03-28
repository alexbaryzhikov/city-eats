package com.example.cityeats.di

import com.example.common.di.ForRestaurantsApi
import com.example.common.di.UnsafeHttpClient
import com.example.venues.VenuesInteractor
import com.example.venues_impl.RestaurantsApi
import com.example.venues_impl.VenuesInteractorImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val RESTAURANTS_BASE_URL = " https://restaurant-api.wolt.com/"

@InstallIn(SingletonComponent::class)
@Module
abstract class VenuesModule {

    @Singleton
    @Binds
    abstract fun bindVenuesInteractor(impl: VenuesInteractorImpl): VenuesInteractor

    companion object {

        @ForRestaurantsApi
        @Singleton
        @Provides
        fun provideMoshi(): Moshi = Moshi.Builder().build()

        @ForRestaurantsApi
        @Singleton
        @Provides
        fun provideRestaurantsRetrofit(
            @UnsafeHttpClient client: OkHttpClient,
            @ForRestaurantsApi moshi: Moshi
        ): Retrofit =
            Retrofit.Builder()
                .client(client)
                .baseUrl(RESTAURANTS_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        @Singleton
        @Provides
        fun provideRestaurantsApi(@ForRestaurantsApi retrofit: Retrofit): RestaurantsApi =
            retrofit.create(RestaurantsApi::class.java)
    }
}
