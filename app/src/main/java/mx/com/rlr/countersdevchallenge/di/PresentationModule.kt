package mx.com.rlr.countersdevchallenge.di

import mx.com.rlr.countersdevchallenge.di.presentation.homeModule
import org.koin.core.module.Module

fun getPresentationModules(): List<Module> = listOf(
    homeModule
)