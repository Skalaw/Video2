package com.rocksolidapps.movies.ui.discovermovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.rocksolidapps.movies.databinding.FragmentDiscoverMovieBinding
import com.rocksolidapps.movies.ui.BaseFragment

class DiscoverMovieFragment : BaseFragment() {
    lateinit var binding: FragmentDiscoverMovieBinding
    lateinit var discoverMovieAdapter: DiscoverMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDiscoverMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discoverMovieAdapter = DiscoverMovieAdapter()
        binding.rvDiscoverMovie.apply {
            adapter = discoverMovieAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    companion object {
        const val TAG = "DiscoverMovieFragment"
        fun newInstance() = DiscoverMovieFragment()
    }
}