package com.example.viewwork.worktext.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BaseRepository {
    private val apiSchedulers = Schedulers.newThread()

    fun <T> getApi(observable: Single<T>): Single<T> {
        return observable.subscribeOn(apiSchedulers)
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> getAgaApi(observable: Single<T>): Single<T> {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

}