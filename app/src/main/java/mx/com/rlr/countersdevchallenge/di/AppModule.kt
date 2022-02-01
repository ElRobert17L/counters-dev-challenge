package mx.com.rlr.countersdevchallenge.di

import mx.com.rlr.countersdevchallenge.CountersDevChallengeApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun CountersDevChallengeApp.initKoin() {
    val modules = getSharedModules() + getFeatureModules() + getPresentationModules()

    startKoin {
        androidContext(applicationContext)
        modules(modules)
    }

}