package com.example.viewwork.worktext.ui

import androidx.lifecycle.MutableLiveData
import com.example.viewwork.worktext.base.BaseViewModel
import com.example.viewwork.worktext.data.UserListData
import com.example.viewwork.worktext.repository.GitRepository

class MainViewModel(
    private val gitRepository: GitRepository
) : BaseViewModel() {

    val userListLiveData = MutableLiveData<List<UserListData>>()

    fun getUserList(source:Int) {
        compositeDisposable.add(
            gitRepository.getUserList(source)
                .subscribe({
                    userListLiveData.value = it
                }, {

                })
        )

    }
}