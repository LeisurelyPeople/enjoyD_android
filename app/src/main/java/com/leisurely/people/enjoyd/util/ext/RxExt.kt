package com.leisurely.people.enjoyd.util.ext

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// Single
fun <T> Single<T>.applySingleSchedulers() = applyScheduler(Schedulers.io())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())



// Observable
fun <T> Observable<T>.applyObservableSchedulers() = applyScheduler(Schedulers.io())

private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())