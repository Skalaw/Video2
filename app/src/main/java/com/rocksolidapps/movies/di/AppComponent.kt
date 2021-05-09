package com.rocksolidapps.movies.di

import com.rocksolidapps.core.di.DispatcherModule
import com.rocksolidapps.movies.MovieApp
import com.rocksolidapps.movies.ui.BaseFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, ViewModelModule::class, UseCaseModule::class, DispatcherModule::class])
interface AppComponent {

    fun inject(fragment: BaseFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MovieApp): Builder
        fun build(): AppComponent
    }
}