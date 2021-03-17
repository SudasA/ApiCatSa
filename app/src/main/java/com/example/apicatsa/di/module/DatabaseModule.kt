package com.example.apicatsa.di.module

import android.content.Context
import com.example.apicatsa.data.CatsDao
import com.example.apicatsa.data.CatsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideCatsDatabase(context: Context): CatsDatabase =
        CatsDatabase.create(context)

    @Singleton
    @Provides
    fun provideCatsDao(db: CatsDatabase): CatsDao =
        db.catsDao()
}