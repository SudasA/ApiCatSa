package com.example.apicatsa.api

import com.example.apicatsa.api.entity.Cat
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatsApi {

    @GET("/v1/images/search")
    fun search(
        @Header(X_API_KEY_HEADER) header: String,
        @Query("limit") limit: Int?,
        @Query("page") page: Int?,
        @Query("order") order: String?,
        @Query("mime_types") type: String?
    ): Single<List<Cat>>

    companion object {
        private const val X_API_KEY_HEADER = "x-api-key"
    }
}