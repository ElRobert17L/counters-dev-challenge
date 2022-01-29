package mx.com.rlr.internet_connection

import mx.com.rlr.internet_connection.data.InternetConnectionRepository
import mx.com.rlr.internet_connection.data.InternetConnectionRepositoryImpl
import org.koin.dsl.module

val internetConnectionModule = module {

    single<InternetConnectionRepository>(createdAtStart = true) {
        InternetConnectionRepositoryImpl()
    }

}