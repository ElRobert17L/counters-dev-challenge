package mx.com.rlr.counters.domain.use_case.get_counters

import mx.com.rlr.base_use_case.Failure
import mx.com.rlr.core.failureHandler.DefaultFailure
import mx.com.rlr.core.failureHandler.NetworkFailure
import mx.com.rlr.http_client.data.failureManage.HttpFailure
import mx.com.rlr.http_client.data.failureManage.JsonDataFailure

sealed class GetCountersFailure: Failure() {

    object NetworkConnectionFailure: NetworkFailure, GetCountersFailure()

    data class JsonDeserializationFailure(
        override val message: String
    ) : JsonDataFailure, GetCountersFailure()

    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : HttpFailure, GetCountersFailure()

    data class UnknownFailure(
        override val message: String
    ) : DefaultFailure, GetCountersFailure()

}
