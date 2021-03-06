package com.example.apicatsa.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.apicatsa.R
import com.test.testcatsapp.ui.cats.photo.CatPhotoFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {
    private val navController by lazy { findNavController(R.id.catsNavHost) }

    private var skipItemSelectAction = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBottomNavigationView.setupWithNavController(navController)
        mainBottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            if (!skipItemSelectAction) {
                when (navController.currentDestination?.id) {
                    R.id.catsFragment -> {
                        // Cond to prevent incorrect self navigation
                        if (menuItem.itemId == R.id.favoriteCatsMenuItem) {
                            navigateFromCatsToFavoriteCats()
                        }
                    }
                    R.id.favoriteCatsFragment -> {
                        // Cond to prevent incorrect self navigation
                        if (menuItem.itemId == R.id.catsMenuItem) {
                            navigateFromFavoriteCatsToCats()
                        }
                    }
                    R.id.catPhotoFragment -> {
                        if (menuItem.itemId == R.id.favoriteCatsMenuItem) {
                            navigateFromCatPhotoToFavoriteCats()
                        } else {
                            onBackPressed()
                        }
                    }
                    R.id.favoriteCatPhotoFragment -> {
                        if (menuItem.itemId == R.id.catsMenuItem) {
                            navigateFromFavoriteCatPhotoToCats()
                        } else {
                            onBackPressed()
                        }
                    }
                }
            } else {
                skipItemSelectAction = false
            }
            true
        }
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.catsFragment -> finish()
            R.id.favoriteCatsFragment -> {
                navController.popBackStack()
                // Skip OnNavigationItemSelectedListener call to prevent already completed navigation
                skipItemSelectAction = true
                mainBottomNavigationView.selectedItemId = R.id.catsMenuItem
            }
            else -> super.onBackPressed()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val topFragment = supportFragmentManager.findFragmentById(R.id.catsNavHost)?.childFragmentManager?.fragments?.get(0)
        if (topFragment is CatPhotoFragment) {
            topFragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun navigateFromCatsToFavoriteCats() =
        navController.navigate(R.id.action_from_cats_to_favorite_cats)

    private fun navigateFromFavoriteCatsToCats() =
        navController.navigate(R.id.action_from_favorite_cats_to_cats)

    private fun navigateFromCatPhotoToFavoriteCats() =
        navController.navigate(R.id.action_from_cat_photo_to_favorite_cats)

    private fun navigateFromFavoriteCatPhotoToCats() =
        navController.navigate(R.id.action_from_favorite_cat_photo_to_cats)
}
