package com.example.apicatsa.ui.cats.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.apicatsa.R
import com.example.apicatsa.data.entity.Cat
import com.example.apicatsa.ui.cats.CatsAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cats.*
import javax.inject.Inject

class CatsFragment : DaggerFragment(), Cats.View {
    @Inject
    lateinit var presenter: Cats.Presenter

    @Inject
    lateinit var glide: RequestManager

    private val cats = mutableListOf<Cat>()
    private var catsAdapter: CatsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_cats, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.bind(this)

        catsRefreshView.setOnRefreshListener { presenter.onRefresh() }
        catsAdapter = CatsAdapter(glide, cats, presenter::onCatClicked, ::onCatLongClicked)
        catsView.adapter = catsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbind()
    }

    override fun showProgress() {
        catsView.isVisible = false
        catsProgressView.isVisible = true
        catsPlaceholderView.isVisible = false
    }

    override fun hideProgress() {
        catsProgressView.isVisible = false
    }

    override fun showCats(cats: List<Cat>) {
        this.cats.clear()
        this.cats.addAll(cats)
        catsAdapter?.notifyDataSetChanged()

        catsView.isVisible = true
        catsProgressView.isVisible = false
        catsPlaceholderView.isVisible = false
    }

    override fun showEmptyView() {
        catsView.isVisible = false
        catsProgressView.isVisible = false
        catsPlaceholderView.isVisible = true
        catsPlaceholderView.setTitle(R.string.cats_view_placeholder_empty)
    }

    override fun showError() {
        catsView.isVisible = false
        catsProgressView.isVisible = false
        catsPlaceholderView.isVisible = true
        catsPlaceholderView.setTitle(R.string.cats_view_placeholder_error)
    }

    override fun showFullImage(photoUrl: String) {
        if (findNavController().currentDestination?.id == R.id.catsFragment) {
            val photoBundle = Bundle().apply { putString(PHOTO_URL_BUNDLE_KEY, photoUrl) }
            findNavController().navigate(R.id.action_from_cats_to_cat_photo, photoBundle)
        }
    }

    override fun showCatSavedToast() =
        Toast.makeText(context, R.string.cats_view_save_to_favorite_toast, Toast.LENGTH_SHORT).show()

    override fun stopRefreshing() {
        if (catsRefreshView.isRefreshing) {
            catsRefreshView.isRefreshing = false
        }
    }

    private fun onCatLongClicked(cat: Cat): Boolean {
        presenter.onCatLongClicked(cat)
        return true
    }

    companion object {
        const val PHOTO_URL_BUNDLE_KEY = "photoUrl"
    }
}