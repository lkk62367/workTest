package com.example.viewwork.worktext.base

import androidx.lifecycle.AndroidViewModel
import com.example.viewwork.worktext.App.Companion.appInstance
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : AndroidViewModel(appInstance) {

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}