package com.nicolas.nicolsflix.core

import android.app.Application
import com.nicolas.nicolsflix.di.*
import com.nicolas.nicolsflix.presentation.cast.CastDiModule
import com.nicolas.nicolsflix.presentation.detail.DetailDiModule
import com.nicolas.nicolsflix.presentation.home.HomeDiModule
import com.nicolas.nicolsflix.upcoming.UpcomingDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@KoinApplication)

            modules(
                apiModules,
                detailsModule,
                searchModules,
                databaseModule,
                myListModule,
                newDetailModule,
                UpcomingDiModule.instance,
                HomeDiModule.instance,
                DetailDiModule.instance,
                CastDiModule.instance,
            )
        }
    }
}