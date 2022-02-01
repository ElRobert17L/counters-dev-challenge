package mx.com.rlr.counters.data.remote

import kotlinx.serialization.SerializationException
import mx.com.rlr.base_use_case.Either
import mx.com.rlr.core.failureHandler.message
import mx.com.rlr.counters.data.CountersRemoteDataSource
import mx.com.rlr.counters.data.remote.service.CountersApiService
import mx.com.rlr.counters.data.remote.service.failure.*
import mx.com.rlr.counters.data.remote.service.request.AddCounterRequest
import mx.com.rlr.counters.data.remote.service.request.DecCounterRequest
import mx.com.rlr.counters.data.remote.service.request.DeleteCounterRequest
import mx.com.rlr.counters.data.remote.service.request.IncCounterRequest
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterFailure
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterResponse
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterFailure
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterResponse
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterFailure
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterResponse
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersFailure
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersParams
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersResponse
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterFailure
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterResponse
import mx.com.rlr.http_client.data.retrofitApiCall
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException

internal class CountersRemoteDataSourceImpl(): CountersRemoteDataSource, KoinComponent {

    private val apiService: CountersApiService by inject()

    override suspend fun addCounter(
        title: String
    ): Either<AddCounterFailure, AddCounterResponse> = try {
        retrofitApiCall {
            apiService.addCounter(AddCounterRequest(title = title))
        }.let { countersDto ->
            Either.Right(AddCounterResponse(counters = countersDto.map { it.toCounter() }))
        }
    } catch (exception: Exception) {
        val failure: AddCounterFailure = when (exception) {
            is HttpException -> exception.toAddCounterFailure()
            is SerializationException ->
                AddCounterFailure.JsonDeserializationFailure(exception.message())
            else -> AddCounterFailure.UnknownFailure(exception.message())
        }
        Either.Left(failure)
    }

    override suspend fun decCounter(
        id: String
    ): Either<DecCounterFailure, DecCounterResponse> = try {
        retrofitApiCall {
            apiService.decCounter(DecCounterRequest(id = id))
        }.let { countersDto ->
            Either.Right(DecCounterResponse(counters = countersDto.map { it.toCounter() }))
        }
    } catch (exception: Exception) {
        val failure: DecCounterFailure = when (exception) {
            is HttpException -> exception.toDecCounterFailure()
            is SerializationException ->
                DecCounterFailure.JsonDeserializationFailure(exception.message())
            else -> DecCounterFailure.UnknownFailure(exception.message())
        }
        Either.Left(failure)
    }

    override suspend fun deleteCounter(
        id: String
    ): Either<DeleteCounterFailure, DeleteCounterResponse> = try {
        retrofitApiCall {
            apiService.deleteCounter(DeleteCounterRequest(id = id))
        }.let { countersDto ->
            Either.Right(DeleteCounterResponse(counters = countersDto.map { it.toCounter() }))
        }
    } catch (exception: Exception) {
        val failure: DeleteCounterFailure = when (exception) {
            is HttpException -> exception.toDeleteCounterFailure()
            is SerializationException ->
                DeleteCounterFailure.JsonDeserializationFailure(exception.message())
            else -> DeleteCounterFailure.UnknownFailure(exception.message())
        }
        Either.Left(failure)
    }

    override suspend fun getCounters(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse> = try {
        retrofitApiCall {
            apiService.getCounters()
        }.let { countersDto ->
            Either.Right(GetCountersResponse(counters = countersDto.map { it.toCounter() }))
        }
    } catch (exception: Exception) {
        val failure: GetCountersFailure = when (exception) {
            is HttpException -> exception.toGetCountersFailure()
            is SerializationException ->
                GetCountersFailure.JsonDeserializationFailure(exception.message())
            else -> GetCountersFailure.UnknownFailure(exception.message())
        }
        Either.Left(failure)
    }

    override suspend fun incCounter(
        id: String
    ): Either<IncCounterFailure, IncCounterResponse> = try {
        retrofitApiCall {
            apiService.incCounter(IncCounterRequest(id = id))
        }.let { countersDto ->
            Either.Right(IncCounterResponse(counters = countersDto.map { it.toCounter() }))
        }
    } catch (exception: Exception) {
        val failure: IncCounterFailure = when (exception) {
            is HttpException -> exception.toIncCounterFailure()
            is SerializationException ->
                IncCounterFailure.JsonDeserializationFailure(exception.message())
            else -> IncCounterFailure.UnknownFailure(exception.message())
        }
        Either.Left(failure)
    }

}