package com.leisurely.people.enjoyd.ui.base

import android.app.Application
import com.leisurely.people.enjoyd.BuildConfig
import com.leisurely.people.enjoyd.di.networkModule
import com.leisurely.people.enjoyd.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EnjoyDApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@EnjoyDApplication)
            modules(networkModule, viewModelModule)
        }
    }

    companion object {
        lateinit var instance: EnjoyDApplication
            private set
    }
}