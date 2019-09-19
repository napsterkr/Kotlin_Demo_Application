package com.kuldeep.posts.core.di

import android.content.Context
import com.kuldeep.posts.core.networking.AppScheduler
import com.kuldeep.posts.core.networking.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun scheduler(): Scheduler {
        return AppScheduler()
    }
}