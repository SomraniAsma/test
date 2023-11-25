package com.example.testmobileca.di

import com.example.testmobileca.global.listener.SchedulerProvider
import com.example.testmobileca.global.utils.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SchedulerModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}
