package mx.com.rlr.countersdevchallenge

import android.app.Application
import mx.com.rlr.countersdevchallenge.di.initKoin

class CountersDevChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

}