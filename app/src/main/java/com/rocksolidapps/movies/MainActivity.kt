package com.rocksolidapps.movies

import android.os.Bundle
import androidx.fragment.app.commit
import com.rocksolidapps.movies.ui.BaseActivity
import com.rocksolidapps.movies.ui.discovermovie.DiscoverMovieFragment

class MainActivity : BaseActivity() {
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
}