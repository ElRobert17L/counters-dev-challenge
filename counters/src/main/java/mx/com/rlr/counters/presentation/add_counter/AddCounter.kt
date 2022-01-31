package mx.com.rlr.counters.presentation.add_counter

import androidx.lifecycle.LiveData
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterFailure
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterResponse

typealias AddCounterStatus = Status<AddCounterFailure, AddCounterResponse>

interface AddCounter {

    fun addCounterAsLiveData(
        title: String
    ): LiveData<AddCounterStatus>

}