package com.example.apicatsa.ui.cats.favorite

import com.example.apicatsa.data.entity.Cat
import com.example.apicatsa.repository.CatsRepository
import com.example.apicatsa.ui.cats.favorite.FavoriteCats
import javax.inject.Inject

class FavoriteCatsModel @Inject constructor(
    private val catsRepository: CatsRepository
) : FavoriteCats.Model {

    override fun catsFromDb() =
        catsRepository.fromDb()

    override fun deleteCatFromFavorite(cat: Cat) =
        catsRepository.deleteFavorite(cat)

    override fun saveFavoriteCat(cat: Cat) =
        catsRepository.saveFavorite(cat)
}