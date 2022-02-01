package mx.com.rlr.internet_connection.data.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.haveNetworkConnection(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = connectivityManager.activeNetwork ?: return false

    val networkCapabilities =
        connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

    return when {
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}