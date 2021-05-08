package com.rocksolidapps.movies.data.repository

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersInjector {
    val ui: Scheduler
    val io: Scheduler
    val computation: Scheduler
}