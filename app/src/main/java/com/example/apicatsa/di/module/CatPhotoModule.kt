package com.example.apicatsa.di.module

import android.content.Context
import com.example.apicatsa.ui.cats.photo.CatPhoto
import com.example.apicatsa.ui.cats.photo.CatPhotoModel
import com.example.apicatsa.ui.cats.photo.CatPhotoPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatPhotoModule {
    @Singleton
    @Provides
    fun provideCatPhotoPresenter(context: Context, model: CatPhoto.Model): CatPhoto.Presenter =
        CatPhotoPresenter(context, model)

    @Singleton
    @Provides
    fun provideCatPhotoModel(): CatPhoto.Model =
        CatPhotoModel()
}