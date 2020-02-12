package com.example.viewwork.worktext.ui.user_data

import androidx.lifecycle.MutableLiveData
import com.example.viewwork.worktext.base.BaseViewModel
import com.example.viewwork.worktext.data.UserData
import com.example.viewwork.worktext.repository.GitRepository

class UserDataViewModel(
    private val gitRepository: GitRepository
) : BaseViewModel() {

    val userDataLiveData = MutableLiveData<UserData>()

    fun getUserData(userName: String) {
        compositeDisposable.add(
            gitRepository.getUserData(userName)
                .subscribe({
                    userDataLiveData.value = it
                }, {

                })
        )
    }

}