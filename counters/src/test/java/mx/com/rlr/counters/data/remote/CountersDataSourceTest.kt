package mx.com.rlr.counters.data.remote

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import mx.com.rlr.base_use_case.Either
import mx.com.rlr.counters.data.CountersRemoteDataSource
import mx.com.rlr.counters.data.remote.model.dto.CounterDto
import mx.com.rlr.counters.data.remote.service.CountersApiService
import mx.com.rlr.counters.data.remote.service.request.AddCounterRequest
import mx.com.rlr.counters.data.remote.service.request.DecCounterRequest
import mx.com.rlr.counters.data.remote.service.request.DeleteCounterRequest
import mx.com.rlr.counters.data.remote.service.request.IncCounterRequest
import mx.com.rlr.counters.domain.use_case.add_counter.AddCounterFailure
import mx.com.rlr.counters.domain.use_case.dec_counter.DecCounterFailure
import mx.com.rlr.counters.domain.use_case.delete_counter.DeleteCounterFailure
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersFailure
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersParams
import mx.com.rlr.counters.domain.use_case.inc_counter.IncCounterFailure
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class CountersDataSourceTest {

    private lateinit var countersDataSource: CountersRemoteDataSource

    @MockK
    private lateinit var apiService: CountersApiService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        countersDataSource = CountersRemoteDataSourceImpl()
    }

    @Test
    fun getCountersFailureWithServerFailure() {
        val response: Response<List<CounterDto>> = mockk()
        val messageFailure = "Server failure"
        val code = 500

        every { response.isSuccessful } returns false
        every { response.body() } returns null
        every { response.code() } returns code
        every { response.message() } returns messageFailure
        coEvery { apiService.getCounters() } returns response

        val result = runBlocking { countersDataSource.getCounters(GetCountersParams) }
        val expectedResult = Either.Left(GetCountersFailure.ServerFailure(code, messageFailure))
        Assert.assertEquals(result, expectedResult)
    }

    @Test
    fun addCounterFailureWithServerFailure() {
        val response: Response<List<CounterDto>> = mockk()
        val messageFailure = "Server failure"
        val code = 500

        every { response.isSuccessful } returns false
        every { response.body() } returns null
        every { response.code() } returns code
        every { response.message() } returns messageFailure
        coEvery { apiService.addCounter(AddCounterRequest(title = "")) } returns response

        val result = runBlocking { countersDataSource.addCounter(title = "") }
        val expectedResult = Either.Left(AddCounterFailure.ServerFailure(code, messageFailure))
        Assert.assertEquals(result, expectedResult)
    }

    @Test
    fun deleteCounterFailureWithServerFailure() {
        val response: Response<List<CounterDto>> = mockk()
        val messageFailure = "Server failure"
        val code = 500

        every { response.isSuccessful } returns false
        every { response.body() } returns null
        every { response.code() } returns code
        every { response.message() } returns messageFailure
        coEvery { apiService.deleteCounter(DeleteCounterRequest(id = "")) } returns response

        val result = runBlocking { countersDataSource.deleteCounter(id = "") }
        val expectedResult = Either.Left(DeleteCounterFailure.ServerFailure(code, messageFailure))
        Assert.assertEquals(result, expectedResult)
    }

    @Test
    fun incCounterFailureWithServerFailure() {
        val response: Response<List<CounterDto>> = mockk()
        val messageFailure = "Server failure"
        val code = 500

        every { response.isSuccessful } returns false
        every { response.body() } returns null
        every { response.code() } returns code
        every { response.message() } returns messageFailure
        coEvery { apiService.incCounter(IncCounterRequest(id = "")) } returns response

        val result = runBlocking { countersDataSource.incCounter(id = "") }
        val expectedResult = Either.Left(IncCounterFailure.ServerFailure(code, messageFailure))
        Assert.assertEquals(result, expectedResult)
    }

    @Test
    fun decCounterFailureWithServerFailure() {
        val response: Response<List<CounterDto>> = mockk()
        val messageFailure = "Server failure"
        val code = 500

        every { response.isSuccessful } returns false
        every { response.body() } returns null
        every { response.code() } returns code
        every { response.message() } returns messageFailure
        coEvery { apiService.decCounter(DecCounterRequest(id = "")) } returns response

        val result = runBlocking { countersDataSource.decCounter(id = "") }
        val expectedResult = Either.Left(DecCounterFailure.ServerFailure(code, messageFailure))
        Assert.assertEquals(result, expectedResult)
    }

}