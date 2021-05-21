package com.rocksolidapps.movies.ui.detailmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rocksolidapps.movies.R
import com.rocksolidapps.movies.databinding.FragmentDetailMovieBinding
import com.rocksolidapps.movies.ext.viewCoroutineScope
import com.rocksolidapps.movies.ui.BaseFragment
import com.rocksolidapps.movies.ui.DataState
import kotlinx.coroutines.flow.collect

class DetailMovieFragment : BaseFragment() {
    lateinit var binding: FragmentDetailMovieBinding

    private val viewModel: DetailMovieViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCoroutineScope.launchWhenStarted {
            viewModel.movieDetailStateFlow.collect { dataState ->
                when (dataState) {
                    is DataState.Data -> binding.movie = dataState.data
                    is DataState.LoadingState -> {
                        // TODO:
                    }
                    is DataState.ErrorState -> {
                        // TODO:
                    }
                }
            }
        }
        if (savedInstanceState == null) {
            viewModel.initViewModel(argumentMovieId())
        }
    }

    fun argumentMovieId() = arguments?.getInt(ARG_MOVIE_ID) ?: 0

    companion object {
        const val TAG = "DetailMovieFragment"
        private const val ARG_MOVIE_ID = "MOVIE_ID"
        fun newInstance(movieId: Int) = DetailMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_MOVIE_ID, movieId)
            }
        }
    }
}