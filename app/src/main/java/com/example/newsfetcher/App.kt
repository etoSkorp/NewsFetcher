package com.example.newsfetcher

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.newsfetcher.di.databaseModule
import com.example.newsfetcher.di.networkModule
import com.example.newsfetcher.feature.bookmark.di.bookmarksModule
import com.example.newsfetcher.feature.mainscreen.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, mainScreenModule, databaseModule, bookmarksModule)
        }

        // Чтобы приложение использовало темную тему в зависимости от системы
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}