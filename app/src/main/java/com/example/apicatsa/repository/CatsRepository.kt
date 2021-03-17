package com.example.apicatsa.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import com.example.apicatsa.api.entity.Cat as ApiCat
import com.example.apicatsa.data.entity.Cat as DbCat

interface CatsRepository {
    fun fromDb(): Observable<List<DbCat>>

    fun fromApi(): Single<List<ApiCat>>

    fun saveFavorite(cat: DbCat) : Completable
    fun deleteFavorite(cat: DbCat) : Completable
}