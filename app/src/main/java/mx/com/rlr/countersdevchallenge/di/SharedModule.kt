package mx.com.rlr.countersdevchallenge.di

import mx.com.rlr.http_client.httpClientModule
import mx.com.rlr.internet_connection.internetConnectionModule
import org.koin.core.module.Module

fun getSharedModules(): List<Module> = listOf(
    internetConnectionModule,
    httpClientModule
)