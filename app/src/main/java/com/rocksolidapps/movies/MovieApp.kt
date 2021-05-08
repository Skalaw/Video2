package com.rocksolidapps.movies

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import com.rocksolidapps.movies.di.AppComponent
import com.rocksolidapps.movies.di.DaggerAppComponent

class MovieApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}

val Context.appComponent get() = (applicationContext as MovieApp).appComponent
val Fragment.appComponent get() = requireContext().appComponent