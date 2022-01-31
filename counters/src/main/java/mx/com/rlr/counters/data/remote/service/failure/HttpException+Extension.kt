package mx.com.rlr.counters.data.remote.service.failure

import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterFailure
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterFailure
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterFailure
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersFailure
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterFailure
import mx.com.rlr.http_client.data.HttpErrorCode
import retrofit2.HttpException

internal fun HttpException.toAddCounterFailure(): AddCounterFailure =
    when (HttpErrorCode.from(code())) {
        else -> AddCounterFailure.ServerFailure(code(), message())
    }

internal fun HttpException.toDecCounterFailure(): DecCounterFailure =
    when (HttpErrorCode.from(code())) {
        else -> DecCounterFailure.ServerFailure(code(), message())
    }

internal fun HttpException.toDeleteCounterFailure(): DeleteCounterFailure =
    when (HttpErrorCode.from(code())) {
        else -> DeleteCounterFailure.ServerFailure(code(), message())
    }

internal fun HttpException.toGetCountersFailure(): GetCountersFailure =
    when (HttpErrorCode.from(code())) {
        else -> GetCountersFailure.ServerFailure(code(), message())
    }

internal fun HttpException.toIncCounterFailure(): IncCounterFailure =
    when (HttpErrorCode.from(code())) {
        else -> IncCounterFailure.ServerFailure(code(), message())
    }