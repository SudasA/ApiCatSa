package com.example.apicatsa.di.module

import com.example.apicatsa.api.CatsApi
import com.example.apicatsa.data.CatsDao
import com.example.apicatsa.repository.CatsRepository
import com.example.apicatsa.repository.DefaultCatsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCatsRepository(catsDao: CatsDao, catsApi: CatsApi): CatsRepository =
        DefaultCatsRepository(catsDao, catsApi)
}