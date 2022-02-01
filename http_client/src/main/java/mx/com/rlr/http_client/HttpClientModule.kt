package mx.com.rlr.http_client

import mx.com.rlr.http_client.data.RetrofitBuilder
import org.koin.dsl.module
import retrofit2.Retrofit

val httpClientModule = module {

    single<Retrofit> {
        RetrofitBuilder().build()
    }

}