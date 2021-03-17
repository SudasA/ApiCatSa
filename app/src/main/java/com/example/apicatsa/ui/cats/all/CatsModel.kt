package com.example.apicatsa.ui.cats.all

import com.example.apicatsa.common.mappers.toDbEntities
import com.example.apicatsa.data.entity.Cat
import com.example.apicatsa.repository.CatsRepository
import com.example.apicatsa.ui.cats.all.Cats
import javax.inject.Inject

class CatsModel @Inject constructor(
    private val catsRepository: CatsRepository
) : Cats.Model {

    override fun cats() =
        catsRepository.fromApi()
            .map { apiCats -> apiCats.toDbEntities() }

    override fun setCatFavorite(cat: Cat) =
        catsRepository.saveFavorite(cat)
}
