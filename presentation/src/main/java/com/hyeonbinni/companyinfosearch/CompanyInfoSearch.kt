package com.hyeonbinni.companyinfosearch

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CompanyInfoSearch : Application() {

    override fun onCreate() {
        initKoin()

        super.onCreate()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(appModules)
        }
    }
}