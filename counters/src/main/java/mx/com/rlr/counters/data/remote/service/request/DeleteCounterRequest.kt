package mx.com.rlr.counters.data.remote.service.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DeleteCounterRequest(
    @SerialName("id") val id: String
)