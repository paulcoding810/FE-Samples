package com.paulcoding.fesamples

import android.app.Application
import com.paulcoding.fesamples.core.domain.repositoryModule
import com.paulcoding.fesamples.core.network.di.networkModule
import com.paulcoding.fesamples.feature.qroverlay.cameraModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    cameraModule,
                )
            )
        }
    }
}