package mx.com.rlr.counters.domain.use_case.dec_counter

import mx.com.rlr.base_use_case.Either
import mx.com.rlr.base_use_case.UseCase
import mx.com.rlr.counters.domain.CountersRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DecCounterUseCase: UseCase<DecCounterResponse, String, DecCounterFailure>(), KoinComponent {

    private val repository: CountersRepository by inject()

    override suspend fun run(
        params: String
    ): Either<DecCounterFailure, DecCounterResponse> =
        repository.decCounter(params)

}