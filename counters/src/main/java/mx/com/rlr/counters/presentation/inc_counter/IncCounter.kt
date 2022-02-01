package mx.com.rlr.counters.presentation.inc_counter

import androidx.lifecycle.LiveData
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterFailure
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterResponse

typealias IncCounterStatus = Status<IncCounterFailure, IncCounterResponse>

interface IncCounter {

    fun incCounterAsLiveData(
        id: String
    ): LiveData<IncCounterStatus>

}