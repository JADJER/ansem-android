package me.jadjer.ansem.app

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import me.jadjer.ansem.app.di.*
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                viewModelModule,
                repositoryModule,
                netModule,
                apiModule,
                databaseModule
            )
        }
    }
}