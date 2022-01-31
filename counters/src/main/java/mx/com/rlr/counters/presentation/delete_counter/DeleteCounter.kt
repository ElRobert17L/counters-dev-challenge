package mx.com.rlr.counters.presentation.delete_counter

import androidx.lifecycle.LiveData
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterFailure
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterResponse

typealias DeleteCounterStatus = Status<DeleteCounterFailure, DeleteCounterResponse>

interface DeleteCounter {

    fun deleteCounterAsLiveData(
        id: String
    ): LiveData<DeleteCounterStatus>

}