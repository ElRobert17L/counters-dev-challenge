package mx.com.rlr.internet_connection.data

import androidx.lifecycle.LiveData

sealed interface InternetConnectionRepository {

    val isOnline: Boolean

    val isOnlineLiveData: LiveData<Boolean>

    suspend fun fetchIsOnline()

}