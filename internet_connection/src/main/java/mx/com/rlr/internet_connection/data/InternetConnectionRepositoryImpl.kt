package mx.com.rlr.internet_connection.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.com.rlr.internet_connection.data.extension.haveNetworkConnection
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class InternetConnectionRepositoryImpl: InternetConnectionRepository, KoinComponent {

    private val context: Context by inject()

    private var _isOnline: Boolean = false
    override val isOnline: Boolean get() = _isOnline

    private var _isOnlineLiveData = MutableLiveData<Boolean>()
    override val isOnlineLiveData: LiveData<Boolean> get() = _isOnlineLiveData

    override suspend fun fetch() {
       _isOnline = try {
           context.haveNetworkConnection()
       } catch (e: Exception) {
           false
       }
        _isOnlineLiveData.value = _isOnline
    }

}