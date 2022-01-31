package mx.com.rlr.counters.presentation.get_counters

import androidx.lifecycle.LiveData
import mx.com.rlr.base_use_case.Either
import mx.com.rlr.base_use_case.onLeft
import mx.com.rlr.base_use_case.onRight
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersFailure
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersParams
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersResponse
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCountersImpl: GetCounters, KoinComponent {

    private val getCountersUseCase: GetCountersUseCase by inject()

    override var getCountersResponse: GetCountersResponse = GetCountersResponse(
        counters = emptyList()
    )

    override var getCountersFailure: GetCountersFailure? = null

    override fun getCountersAsLiveData(
        params: GetCountersParams
    ): LiveData<GetCountersStatus> {
        TODO("Not yet implemented")
    }

    override suspend fun getCountersAsEither(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse> =
        getCountersUseCase.run(params)
            .onLeft {
                getCountersFailure = it
                getCountersResponse = GetCountersResponse(counters = emptyList())
            }
            .onRight {
                getCountersResponse = it
                getCountersFailure = null
            }

}