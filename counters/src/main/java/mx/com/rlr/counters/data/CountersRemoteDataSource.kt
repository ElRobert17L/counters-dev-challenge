package mx.com.rlr.counters.data

import mx.com.rlr.base_use_case.Either
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

interface CountersRemoteDataSource {

    suspend fun addCounter(
        title: String
    ): Either<AddCounterFailure, AddCounterResponse>

    suspend fun decCounter(
        id: String
    ): Either<DecCounterFailure, DecCounterResponse>

    suspend fun deleteCounter(
        id: String
    ): Either<DeleteCounterFailure, DeleteCounterResponse>

    suspend fun getCounters(
        id: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse>

    suspend fun incCounter(
        id: String
    ): Either<IncCounterFailure, IncCounterResponse>

}