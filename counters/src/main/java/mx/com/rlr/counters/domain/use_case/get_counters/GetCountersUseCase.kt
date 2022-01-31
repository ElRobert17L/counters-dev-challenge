package mx.com.rlr.counters.domain.use_case.get_counters

import mx.com.rlr.base_use_case.Either
import mx.com.rlr.base_use_case.UseCase
import mx.com.rlr.counters.domain.CountersRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetCountersUseCase:
    UseCase<GetCountersResponse, GetCountersParams, GetCountersFailure>(), KoinComponent {

    private val repository: CountersRepository by inject()

    override suspend fun run(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse> =
        repository.getCounters(params)

}