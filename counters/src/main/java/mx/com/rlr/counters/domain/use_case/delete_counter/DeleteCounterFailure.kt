package mx.com.rlr.counters.domain.use_case.delete_counter

import mx.com.rlr.base_use_case.Failure
import mx.com.rlr.core.failureHandler.DefaultFailure
import mx.com.rlr.core.failureHandler.NetworkFailure
import mx.com.rlr.http_client.data.failureManage.HttpFailure
import mx.com.rlr.http_client.data.failureManage.JsonDataFailure

sealed class DeleteCounterFailure: Failure() {

    object NetworkConnectionFailure: NetworkFailure, DeleteCounterFailure()

    data class JsonDeserializationFailure(
        override val message: String
    ) : JsonDataFailure, DeleteCounterFailure()

    data class ServerFailure(
        override val code: Int,
        override val message: String
    ) : HttpFailure, DeleteCounterFailure()

    data class UnknownFailure(
        override val message: String
    ) : DefaultFailure, DeleteCounterFailure()

}
