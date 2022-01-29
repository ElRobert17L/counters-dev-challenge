package mx.com.rlr.internet_connection.data

import android.content.Context
import android.net.ConnectivityManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber


object InternetConnectionState : KoinComponent {

    private val context: Context by inject()

    fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        Timber.d("isOnline_ ${connectivityManager.isDefaultNetworkActive}")

       return connectivityManager.isDefaultNetworkActive
    }

}