package com.lnsergey.softbalance.app.di

import com.lnsergey.softbalance.app.view_model.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, ApiModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}