package com.rocksolidapps.movies.data.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulersInjectorImpl : SchedulersInjector {
    override val ui: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val io: Scheduler
        get() = Schedulers.io()
    override val computation: Scheduler
        get() = Schedulers.computation()
}