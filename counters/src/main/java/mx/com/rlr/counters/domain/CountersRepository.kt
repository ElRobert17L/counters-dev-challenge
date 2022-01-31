package mx.com.rlr.counters.domain

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

interface CountersRepository {

    suspend fun addCounter(
        params: String
    ): Either<AddCounterFailure, AddCounterResponse>

    suspend fun decCounter(
        params: String
    ): Either<DecCounterFailure, DecCounterResponse>

    suspend fun deleteCounter(
        params: String
    ): Either<DeleteCounterFailure, DeleteCounterResponse>

    suspend fun getCounters(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse>

    suspend fun incCounter(
        params: String
    ): Either<IncCounterFailure, IncCounterResponse>

}