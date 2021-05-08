package com.rocksolidapps.movies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rocksolidapps.movies.ui.discovermovie.DiscoverMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverMovieViewModel::class)
    fun discoverMovieViewModel(viewModel: DiscoverMovieViewModel): ViewModel

    companion object {
        @Provides
        fun providesViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    val creator = creators[modelClass]
                        ?: creators.asIterable()
                            .firstOrNull { (key, _) -> modelClass.isAssignableFrom(key) }?.value
                        ?: throw IllegalArgumentException("Unknown model class: $modelClass")
                    return creator.get() as T
                }
            }
        }
    }
}