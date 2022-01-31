package mx.com.rlr.counters.data.remote.service.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DecCounterRequest(
    @SerialName("id") val id: String
)