package mx.com.rlr.counters.presentation.add_counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.base_use_case.onLeft
import mx.com.rlr.base_use_case.onRight
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddCounterImpl: AddCounter, KoinComponent {

    private val addCounterUseCase: AddCounterUseCase by inject()

    override fun addCounterAsLiveData(
        title: String
    ): LiveData<AddCounterStatus> = flow<AddCounterStatus> {
        emit(Status.Loading())
        addCounterUseCase.run(title)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

}