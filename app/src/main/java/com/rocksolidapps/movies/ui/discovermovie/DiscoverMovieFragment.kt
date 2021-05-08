package com.rocksolidapps.movies.ui.discovermovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rocksolidapps.movies.databinding.FragmentDiscoverMovieBinding
import com.rocksolidapps.movies.ui.BaseFragment
import com.rocksolidapps.movies.view.recyclerview.PaginationScrollListener

class DiscoverMovieFragment : BaseFragment() {
    lateinit var binding: FragmentDiscoverMovieBinding
    lateinit var discoverMovieAdapter: DiscoverMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDiscoverMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discoverMovieAdapter = DiscoverMovieAdapter()
        binding.rvDiscoverMovie.apply {
            adapter = discoverMovieAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager?.let {
                if (it !is LinearLayoutManager) return@let
                addOnScrollListener(object : PaginationScrollListener(it) {
                    override fun loadMoreItems() {
                        binding.swipeRefreshLayout.isRefreshing = true
                        // TODO: fetch from ViewModel
                    }

                    override val isLastPage: Boolean
                        get() = false // TODO: get from ViewModel
                    override val isLoading: Boolean
                        get() = false // TODO: get from ViewModel
                })
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener { /* TODO: ViewModel */ }
        // TODO: remember binding.swipeRefreshLayout.isRefreshing = false , when new items is fetched
    }

    companion object {
        const val TAG = "DiscoverMovieFragment"
        fun newInstance() = DiscoverMovieFragment()
    }
}