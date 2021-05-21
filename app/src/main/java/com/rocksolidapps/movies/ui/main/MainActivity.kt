package com.rocksolidapps.movies.ui.main

import android.os.Bundle
import androidx.fragment.app.commit
import com.rocksolidapps.movies.R
import com.rocksolidapps.movies.ui.BaseActivity
import com.rocksolidapps.movies.ui.detailmovie.DetailMovieFragment
import com.rocksolidapps.movies.ui.discovermovie.DiscoverMovieFragment

class MainActivity : BaseActivity(), MainActivityUi {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, DiscoverMovieFragment.newInstance(), DiscoverMovieFragment.TAG)
            }
        }
    }

    override fun openDetailsMovie(movieId: Int) {
        supportFragmentManager.commit {
            hide(supportFragmentManager.fragments.last())
            add(R.id.fragmentContainer, DetailMovieFragment.newInstance(movieId), DetailMovieFragment.TAG)
            addToBackStack(DetailMovieFragment.TAG)
        }
    }
}