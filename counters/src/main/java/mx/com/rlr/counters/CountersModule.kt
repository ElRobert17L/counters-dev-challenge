package mx.com.rlr.counters

import mx.com.rlr.counters.data.CountersRemoteDataSource
import mx.com.rlr.counters.data.CountersRepositoryImpl
import mx.com.rlr.counters.data.remote.CountersRemoteDataSourceImpl
import mx.com.rlr.counters.data.remote.service.CountersApiService
import mx.com.rlr.counters.domain.CountersRepository
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterUseCase
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterUseCase
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterUseCase
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersUseCase
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterUseCase
import mx.com.rlr.counters.presentation.add_counter.AddCounter
import mx.com.rlr.counters.presentation.add_counter.AddCounterImpl
import mx.com.rlr.counters.presentation.dec_counter.DecCounter
import mx.com.rlr.counters.presentation.dec_counter.DecCounterImpl
import mx.com.rlr.counters.presentation.delete_counter.DeleteCounter
import mx.com.rlr.counters.presentation.delete_counter.DeleteCounterImpl
import mx.com.rlr.counters.presentation.get_counters.GetCounters
import mx.com.rlr.counters.presentation.get_counters.GetCountersImpl
import mx.com.rlr.counters.presentation.inc_counter.IncCounter
import mx.com.rlr.counters.presentation.inc_counter.IncCounterImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val countersModule = module {

    /** PRESENTATION **/
    single<AddCounter> { AddCounterImpl() }
    single<DecCounter> { DecCounterImpl() }
    single<DeleteCounter> { DeleteCounterImpl() }
    single<GetCounters> { GetCountersImpl() }
    single<IncCounter> { IncCounterImpl() }

    /** USE CASE **/
    factory { AddCounterUseCase() }
    factory { DecCounterUseCase() }
    factory { DeleteCounterUseCase() }
    factory { GetCountersUseCase() }
    factory { IncCounterUseCase() }

    /** REPOSITORY **/
    single<CountersRepository> { CountersRepositoryImpl() }

    /** DATA SOURCE **/
    single<CountersRemoteDataSource> { CountersRemoteDataSourceImpl() }

    /** API SERVICE **/
    single { get<Retrofit>().create(CountersApiService::class.java) }

}