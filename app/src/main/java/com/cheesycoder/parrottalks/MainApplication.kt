package com.cheesycoder.parrottalks

import android.app.Application
import com.cheesycoder.parrottalks.data.dataModule
import com.cheesycoder.parrottalks.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                viewModelModule,
                dataModule
            )
        }
    }
}
