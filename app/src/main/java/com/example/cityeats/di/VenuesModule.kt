package com.example.cityeats.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.common.di.ForRestaurantsApi
import com.example.common.di.SafeHttpClient
import com.example.venues.VenuesInteractor
import com.example.venues_impl.RestaurantsApi
import com.example.venues_impl.VenuesDataStorage
import com.example.venues_impl.VenuesInteractorImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            @SafeHttpClient client: OkHttpClient,
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

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = VenuesDataStorage.PREFS_NAME)

        @Singleton
        @Provides
        fun provideVenuesDataStorage(@ApplicationContext context: Context): VenuesDataStorage {
            return VenuesDataStorage(context.dataStore)
        }
    }
}
