package mx.com.rlr.counters.presentation.inc_counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.base_use_case.onLeft
import mx.com.rlr.base_use_case.onRight
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IncCounterImpl: IncCounter, KoinComponent {

    private val incCounterUseCase: IncCounterUseCase by inject()

    override fun deleteCounterAsLiveData(
        id: String
    ): LiveData<IncCounterStatus> = flow<IncCounterStatus> {
        emit(Status.Loading())
        incCounterUseCase.run(id)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

}