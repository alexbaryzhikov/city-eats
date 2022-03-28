package com.example.common.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SafeHttpClient

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class UnsafeHttpClient

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ForRestaurantsApi
