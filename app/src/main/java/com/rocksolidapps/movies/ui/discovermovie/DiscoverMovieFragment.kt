package com.rocksolidapps.movies.ui.discovermovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rocksolidapps.movies.databinding.FragmentDiscoverMovieBinding
import com.rocksolidapps.movies.ext.viewCoroutineScope
import com.rocksolidapps.movies.ui.BaseFragment
import com.rocksolidapps.movies.view.recyclerview.PaginationScrollListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DiscoverMovieFragment : BaseFragment() {
    lateinit var binding: FragmentDiscoverMovieBinding
    lateinit var discoverMovieAdapter: DiscoverMovieAdapter

    private val viewModel: DiscoverMovieViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDiscoverMovieBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discoverMovieAdapter = DiscoverMovieAdapter {
            // TODO click item
        }
        binding.rvDiscoverMovie.apply {
            adapter = discoverMovieAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            layoutManager?.let {
                if (it !is LinearLayoutManager) return@let
                addOnScrollListener(object : PaginationScrollListener(it) {
                    override fun loadMoreItems() {
                        viewModel.fetchDiscoverMovie()
                    }

                    override val isLastPage: Boolean
                        get() = viewModel.isLastPage.value
                    override val isLoading: Boolean
                        get() = viewModel.movieList.value.isLoading
                })
            }
        }
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.refreshDiscoverMovie() }

        viewCoroutineScope.launch {
            viewModel.movieList.collect {
                binding.swipeRefreshLayout.isRefreshing = it.isLoading
                discoverMovieAdapter.submitList(it.data)
            }
        }
    }

    companion object {
        const val TAG = "DiscoverMovieFragment"
        fun newInstance() = DiscoverMovieFragment()
    }
}