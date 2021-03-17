package com.example.apicatsa.ui.cats.favorite

import com.example.apicatsa.data.entity.Cat
import io.reactivex.Completable
import io.reactivex.Observable

interface FavoriteCats {
    interface Model {
        fun catsFromDb(): Observable<List<Cat>>
        fun deleteCatFromFavorite(cat: Cat): Completable
        fun saveFavoriteCat(cat: Cat): Completable
    }

    interface View {
        fun showProgress()
        fun hideProgress()

        fun showContent(cats: List<Cat>)
        fun showEmptyView()
        fun showError()
        fun showFullImage(photoUrl: String)

        fun showUndoDeleteCatSnackbar(cat: Cat)
    }

    interface Presenter {
        fun bind(view: View)
        fun unbind()

        fun onCatClicked(cat: Cat)
        fun onCatLongClicked(cat: Cat)
        fun onUndoDeleteCatClicked(cat: Cat)
    }
}