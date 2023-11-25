package com.example.testmobileca.global.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


fun Context?.isInternetAvailable(): Boolean {
    if (this == null) return false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    cm.activeNetwork?.let {
        val nc = cm.getNetworkCapabilities(it)

        return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        )
    }

    return false
}

