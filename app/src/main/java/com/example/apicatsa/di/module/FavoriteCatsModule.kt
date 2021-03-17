package com.example.apicatsa.di.module

import com.example.apicatsa.repository.CatsRepository
import com.example.apicatsa.ui.cats.favorite.FavoriteCats
import com.example.apicatsa.ui.cats.favorite.FavoriteCatsModel
import com.example.apicatsa.ui.cats.favorite.FavoriteCatsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoriteCatsModule {
    @Singleton
    @Provides
    fun provideFavoriteCatsModel(catsRepository: CatsRepository): FavoriteCats.Model =
        FavoriteCatsModel(catsRepository)

    @Singleton
    @Provides
    fun provideFavoriteCatsPresenter(model: FavoriteCats.Model): FavoriteCats.Presenter =
        FavoriteCatsPresenter(model)
}