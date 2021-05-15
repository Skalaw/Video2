package com.rocksolidapps.movies

import com.rocksolidapps.movies.data.repository.SchedulersInjector
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class TestSchedulerProvider : SchedulersInjector {
    override val ui: Scheduler = Schedulers.trampoline()
    override val io: Scheduler = Schedulers.trampoline()
    override val computation: Scheduler = Schedulers.trampoline()
}