package com.lnsergey.softbalance.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private var appContext: Context
) {

    @Provides @Singleton fun provideContext() : Context {
        return appContext
    }
}