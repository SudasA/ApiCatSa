package com.example.apicatsa.common.mappers

import com.example.apicatsa.api.entity.Cat as ApiCat
import com.example.apicatsa.data.entity.Cat as DbCat

fun List<ApiCat>.toDbEntities(): List<DbCat> =
    map { it.toDbEntity() }

private fun ApiCat.toDbEntity(): DbCat =
    DbCat(
        id = id,
        imageUrl = url
    )
