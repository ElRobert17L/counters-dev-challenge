package mx.com.rlr.internet_connection.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.core.component.KoinComponent

class InternetConnectionRepositoryImpl(): InternetConnectionRepository, KoinComponent {

    private var _isOnline: Boolean = false
    override val isOnline: Boolean get() = _isOnline

    private var _isOnlineLiveData = MutableLiveData<Boolean>()
    override val isOnlineLiveData: LiveData<Boolean> get() = _isOnlineLiveData

    override suspend fun fetchIsOnline() {
        _isOnline = InternetConnectionState.isConnected()
    }

}