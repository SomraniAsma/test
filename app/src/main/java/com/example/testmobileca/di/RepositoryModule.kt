package com.example.testmobileca.di

import com.example.testmobileca.data.repository.abs.AccountRepository
import com.example.testmobileca.data.repository.imp.AccountRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAccountRepository(accountImp: AccountRepositoryImp): AccountRepository

}