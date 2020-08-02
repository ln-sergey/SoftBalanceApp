package com.lnsergey.softbalance.app

import android.app.Application
import com.lnsergey.softbalance.app.di.AppComponent
import com.lnsergey.softbalance.app.di.AppModule
import com.lnsergey.softbalance.app.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}