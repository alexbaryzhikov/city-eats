package com.example.cityeats.di

import android.annotation.SuppressLint
import com.example.common.di.SafeHttpClient
import com.example.common.di.UnsafeHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @IntoSet
    @Provides
    fun provideInterceptors(): Interceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    @SafeHttpClient
    @Singleton
    @Provides
    fun provideSafeOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient =
        OkHttpClient.Builder()
            .apply { for (interceptor in interceptors) addInterceptor(interceptor) }
            .build()

    @UnsafeHttpClient
    @Singleton
    @Provides
    fun provideUnsafeOkHttpClient(
        interceptors: Set<@JvmSuppressWildcards Interceptor>,
        @SafeHttpClient fallbackClient: OkHttpClient
    ): OkHttpClient {
        return try {
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
                .apply { init(null, arrayOf(UnsafeX509TrustManager), SecureRandom()) }
            OkHttpClient.Builder()
                .apply { for (interceptor in interceptors) addInterceptor(interceptor) }
                .sslSocketFactory(sslContext.socketFactory, UnsafeX509TrustManager)
                .hostnameVerifier(UnsafeHostnameVerifier)
                .build()
        } catch (e: Throwable) {
            fallbackClient
        }
    }

    @SuppressLint("CustomX509TrustManager", "TrustAllX509TrustManager")
    private object UnsafeX509TrustManager : X509TrustManager {

        @Throws(CertificateException::class)
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            // no-op
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            // no-op
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    }

    @SuppressLint("BadHostnameVerifier")
    private object UnsafeHostnameVerifier : HostnameVerifier {
        override fun verify(hostname: String?, session: SSLSession?): Boolean = true
    }
}
