package com.example.testmobileca.global.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.coroutines.cancellation.CancellationException


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



/**
 * Date format converter
 * @param unixTimestamp  date
 * @param pattern  wanted date format
 * @return formatted date String
 *
 */
fun dateFormat(unixTimestamp: Long, pattern :String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTimestamp), ZoneId.systemDefault())
    return dateTime.format(formatter)
}


/**
 * used for suspend tryCatch
 * @param tryBlock  try block to execute
 * @param catchBlock  catch block to execute
 * @param handleCancellationExceptionManually cancellation exception will manually handled
 *
 */
suspend fun tryCatch(
    tryBlock: suspend () -> Unit,
    catchBlock: suspend (Throwable) -> Unit,
    handleCancellationExceptionManually: Boolean = false
) {
    try {
        tryBlock()
    } catch (e: Throwable) {
        if (e !is CancellationException ||
            handleCancellationExceptionManually
        ) {
            catchBlock(e)
        } else {
            throw e
        }
    }
}

