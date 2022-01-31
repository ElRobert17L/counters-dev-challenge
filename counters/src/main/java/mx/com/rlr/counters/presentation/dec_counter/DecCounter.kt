package mx.com.rlr.counters.presentation.dec_counter

import androidx.lifecycle.LiveData
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterFailure
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterResponse

typealias DecCounterStatus = Status<DecCounterFailure, DecCounterResponse>

interface DecCounter {

    fun decCounterAsLiveData(
        id: String
    ): LiveData<DecCounterStatus>

}