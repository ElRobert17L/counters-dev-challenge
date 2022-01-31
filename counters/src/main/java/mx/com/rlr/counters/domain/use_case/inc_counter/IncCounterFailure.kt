package mx.com.rlr.counters.domain.use_case.inc_counter

import mx.com.rlr.base_use_case.Failure
import mx.com.rlr.core.failureHandler.DefaultFailure
import mx.com.rlr.core.failureHandler.NetworkFailure
import mx.com.rlr.http_client.data.failureManage.HttpFailure
import mx.com.rlr.http_client.data.failureManage.JsonDataFailure

sealed class IncCounterFailure: Failure() {

    object NetworkConnectionFailure: NetworkFailure, IncCounterFailure()

    data class JsonDeserializationFailure(
        override val message: String
    ) : JsonDataFailure, IncCounterFailure()

    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : HttpFailure, IncCounterFailure()

    data class UnknownFailure(
        override val message: String
    ) : DefaultFailure, IncCounterFailure()

}
