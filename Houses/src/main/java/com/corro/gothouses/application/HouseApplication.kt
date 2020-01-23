package com.corro.gothouses.application

import android.app.Application
import com.corro.gothouses.koin.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HouseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HouseApplication)
            modules(loadKoinModules())
        }
    }

    private fun loadKoinModules() = listOf(
        MainModule.netModule,
        MainModule.apiModule,
        MainModule.repositoryModule,
        MainModule.viewModelModule
    )
}