package com.example.jokeexplorer

import android.app.Application
import com.example.jokeexplorer.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JokeExplorerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JokeExplorerApp)
            modules(appModules)
        }
    }
}