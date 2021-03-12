package com.nicolas.nicolsflix.core

import android.app.Application
import com.nicolas.nicolsflix.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)

            modules(
                apiModules,
                homeModules,
                detailsModule,
                searchModules,
                databaseModule,
                myListModule
            )
        }
    }
}