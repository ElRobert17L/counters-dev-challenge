package mx.com.rlr.counters.domain.use_case.dec_counter

import mx.com.rlr.base_use_case.Failure
import mx.com.rlr.core.failureHandler.DefaultFailure
import mx.com.rlr.core.failureHandler.NetworkFailure
import mx.com.rlr.http_client.data.failureManage.HttpFailure
import mx.com.rlr.http_client.data.failureManage.JsonDataFailure

sealed class DecCounterFailure: Failure() {

    object NetworkConnectionFailure: NetworkFailure, DecCounterFailure()

    data class JsonDeserializationFailure(
        override val message: String
    ) : JsonDataFailure, DecCounterFailure()

    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : HttpFailure, DecCounterFailure()

    data class UnknownFailure(
        override val message: String
    ) : DefaultFailure, DecCounterFailure()

}
