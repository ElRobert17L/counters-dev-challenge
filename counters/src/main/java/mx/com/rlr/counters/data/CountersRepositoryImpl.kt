package mx.com.rlr.counters.data

import mx.com.rlr.base_use_case.Either
import mx.com.rlr.counters.domain.CountersRepository
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterFailure
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterResponse
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterFailure
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterResponse
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterFailure
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterResponse
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersFailure
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersParams
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersResponse
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterFailure
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterResponse
import mx.com.rlr.internet_connection.data.InternetConnectionRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class CountersRepositoryImpl : CountersRepository, KoinComponent {

    private val remoteDataSource: CountersRemoteDataSource by inject()

    private val internetConnectionRepository: InternetConnectionRepository by inject()

    override suspend fun addCounter(
        params: String
    ): Either<AddCounterFailure, AddCounterResponse> =
        if (internetConnectionRepository.isOnline)
            remoteDataSource.addCounter(params)
        else Either.Left(AddCounterFailure.NetworkConnectionFailure)

    override suspend fun decCounter(
        params: String
    ): Either<DecCounterFailure, DecCounterResponse> =
        if (internetConnectionRepository.isOnline)
            remoteDataSource.decCounter(params)
        else Either.Left(DecCounterFailure.NetworkConnectionFailure)

    override suspend fun deleteCounter(
        params: String
    ): Either<DeleteCounterFailure, DeleteCounterResponse> =
        if (internetConnectionRepository.isOnline)
            remoteDataSource.deleteCounter(params)
        else Either.Left(DeleteCounterFailure.NetworkConnectionFailure)

    override suspend fun getCounters(
        params: GetCountersParams
    ): Either<GetCountersFailure, GetCountersResponse> = remoteDataSource.getCounters(params)

    override suspend fun incCounter(
        params: String
    ): Either<IncCounterFailure, IncCounterResponse> =
        if (internetConnectionRepository.isOnline)
            remoteDataSource.incCounter(params)
        else Either.Left(IncCounterFailure.NetworkConnectionFailure)

}