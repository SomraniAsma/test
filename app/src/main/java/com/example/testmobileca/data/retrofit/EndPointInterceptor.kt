package com.example.testmobileca.retrofit

import android.content.Context
import com.example.testmobileca.global.utils.isInternetAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class EndPointInterceptor(
    @param:ApplicationContext private val context: Context
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url = request.url.toString()
        if (url.contains("")) {
            if (context.isInternetAvailable()) {
                request = request.newBuilder()
                    .method(request.method, request.body)
                   // .addHeader("Authorization", "Bearer " + dataStoreManager.getSyncData("userToken",""))
                    .build()
            } else {
                throw NetworkNotFoundException()
            }
        }

        return chain.proceed(request)
    }

    class NetworkNotFoundException : IOException("¨Pas d'internet dispo")
}