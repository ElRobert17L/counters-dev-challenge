package mx.com.rlr.counters.data.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import mx.com.rlr.counters.data.remote.service.CountersApiService
import mx.com.rlr.counters.data.remote.service.request.AddCounterRequest
import mx.com.rlr.counters.data.remote.service.request.DecCounterRequest
import mx.com.rlr.counters.data.remote.service.request.DeleteCounterRequest
import mx.com.rlr.counters.data.remote.service.request.IncCounterRequest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.io.InputStreamReader

class CountersApiServiceTest {

    private val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = false
                }.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    private val apiService by lazy {
        retrofit.create(CountersApiService::class.java)
    }

    private lateinit var getCountersJson: String
    private lateinit var addCounterJson: String
    private lateinit var deleteCounterJson: String
    private lateinit var incCounterJson: String
    private lateinit var decCounterJson: String

    @Before
    fun setUp() {
        val readerCounters = InputStreamReader(
            this.javaClass.classLoader?.getResourceAsStream("counters_response.json")
        )

        getCountersJson = readerCounters.readText()
        addCounterJson = readerCounters.readText()
        deleteCounterJson = readerCounters.readText()
        incCounterJson = readerCounters.readText()
        decCounterJson = readerCounters.readText()

        readerCounters.close()
    }

    @Test
    fun getCountersRequestPerformCorrectDeserialization() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(getCountersJson)
                .setResponseCode(200)
        )

        val result = runBlocking { apiService.getCounters() }

        Assert.assertNotNull(result.body())
    }

    @Test
    fun addCounterRequestPerformCorrectDeserialization() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(addCounterJson)
                .setResponseCode(200)
        )

        val result = runBlocking { apiService.addCounter(
            AddCounterRequest(title = "")
        ) }

        Assert.assertNotNull(result.body())
    }

    @Test
    fun deleteCounterRequestPerformCorrectDeserialization() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(deleteCounterJson)
                .setResponseCode(200)
        )

        val result = runBlocking { apiService.deleteCounter(
            DeleteCounterRequest(id = "")
        ) }

        Assert.assertNotNull(result.body())
    }

    @Test
    fun incCounterRequestPerformCorrectDeserialization() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(incCounterJson)
                .setResponseCode(200)
        )

        val result = runBlocking { apiService.incCounter(
            IncCounterRequest(id = "")
        ) }

        Assert.assertNotNull(result.body())
    }

    @Test
    fun decCounterRequestPerformCorrectDeserialization() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(decCounterJson)
                .setResponseCode(200)
        )

        val result = runBlocking { apiService.decCounter(
            DecCounterRequest(id = "")
        ) }

        Assert.assertNotNull(result.body())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}