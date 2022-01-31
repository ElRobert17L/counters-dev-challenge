package mx.com.rlr.counters.data.remote.service

import mx.com.rlr.counters.data.remote.model.dto.CounterDto
import mx.com.rlr.counters.data.remote.service.request.AddCounterRequest
import mx.com.rlr.counters.data.remote.service.request.DecCounterRequest
import mx.com.rlr.counters.data.remote.service.request.DeleteCounterRequest
import mx.com.rlr.counters.data.remote.service.request.IncCounterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

internal interface CountersApiService {

    @GET(URL.GET_COUNTERS)
    suspend fun getCounters(): Response<List<CounterDto>>

    @POST(URL.ADD_COUNTER)
    suspend fun addCounter(
        @Body request: AddCounterRequest
    ): Response<List<CounterDto>>

    @POST(URL.INC_COUNTER)
    suspend fun incCounter(
        @Body request: IncCounterRequest
    ): Response<List<CounterDto>>

    @POST(URL.DEC_COUNTER)
    suspend fun decCounter(
        @Body request: DecCounterRequest
    ): Response<List<CounterDto>>

    @HTTP(method = "DELETE", path = URL.DELETE_COUNTER, hasBody = true)
    suspend fun deleteCounter(
        @Body request: DeleteCounterRequest
    ): Response<List<CounterDto>>

    private object URL {

        const val GET_COUNTERS: String = "counters"

        const val ADD_COUNTER: String = "counter"

        const val INC_COUNTER: String = "counter/inc"

        const val DEC_COUNTER: String = "counter/dec"

        const val DELETE_COUNTER: String = "counter"

    }

}