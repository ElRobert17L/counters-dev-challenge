package mx.com.rlr.http_client.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RetrofitBuilder(
    private val baseUrl: String
) {

    @ExperimentalSerializationApi
    fun build(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideOkHttpClient())
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(Constants.CONTENT_TYPE.toMediaType())
        ).build()

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .build()

    private object Constants {
        const val CONTENT_TYPE = "application/json"
    }

}