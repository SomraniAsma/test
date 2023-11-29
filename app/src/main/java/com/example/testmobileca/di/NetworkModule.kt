package com.example.testmobileca.di

import android.content.Context
import com.example.testmobileca.retrofit.EndPointInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val CONNECT_TIMEOUT = 2
private const val WRITE_TIMEOUT = 15
private const val READ_TIMEOUT = 15

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun endpointInterceptor(context: Context): EndPointInterceptor {
        return EndPointInterceptor( context)
    }

    @Provides
    @Singleton
    fun okHttpClient(
        endpointInterceptor: EndPointInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(endpointInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    @Provides
    @Singleton
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1000 * 1000).toLong()) //10MB Cache
    }
}
