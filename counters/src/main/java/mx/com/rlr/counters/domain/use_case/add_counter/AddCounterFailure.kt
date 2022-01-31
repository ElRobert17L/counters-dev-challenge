package mx.com.rlr.counters.domain.use_case.add_counter

import mx.com.rlr.base_use_case.Failure
import mx.com.rlr.core.failureHandler.DefaultFailure
import mx.com.rlr.core.failureHandler.NetworkFailure
import mx.com.rlr.http_client.data.failureManage.HttpFailure
import mx.com.rlr.http_client.data.failureManage.JsonDataFailure

sealed class AddCounterFailure: Failure() {

 object NetworkConnectionFailure: NetworkFailure, AddCounterFailure()

 data class JsonDeserializationFailure(
  override val message: String
 ) : JsonDataFailure, AddCounterFailure()

 data class ServerFailure(
  override val code: Int,
  override val message: String
 ) : HttpFailure, AddCounterFailure()

 data class UnknownFailure(
  override val message: String
 ) : DefaultFailure, AddCounterFailure()

}