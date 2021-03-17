package com.example.apicatsa.di.module

import com.example.apicatsa.repository.CatsRepository
import com.example.apicatsa.ui.cats.all.Cats
import com.example.apicatsa.ui.cats.all.CatsModel
import com.example.apicatsa.ui.cats.all.CatsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatsModule {
    @Singleton
    @Provides
    fun provideCatsModel(catsRepository: CatsRepository): Cats.Model =
        CatsModel(catsRepository)

    @Singleton
    @Provides
    fun provideCatsPresenter(model: Cats.Model): Cats.Presenter =
        CatsPresenter(model)
}