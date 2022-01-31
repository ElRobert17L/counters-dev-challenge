package mx.com.rlr.counters.presentation.get_counters

import androidx.lifecycle.LiveData
import mx.com.rlr.base_use_case.Either
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersFailure
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersParams
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersResponse

typealias GetCountersStatus = Status<GetCountersFailure, GetCountersResponse>

interface GetCounters {

    var getCountersResponse: GetCountersResponse

    var getCountersFailure: GetCountersFailure?

    fun getCountersAsLiveData(
        params: GetCountersParams
    ): LiveData<GetCountersStatus>

    suspend fun getCountersAsEither(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse>

}