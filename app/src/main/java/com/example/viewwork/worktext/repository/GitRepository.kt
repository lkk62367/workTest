package com.example.viewwork.worktext.repository

import com.example.viewwork.worktext.api.GitApi
import com.example.viewwork.worktext.data.UserData
import com.example.viewwork.worktext.data.UserListData
import io.reactivex.Single

class GitRepository(
    private val api: GitApi
) : BaseRepository() {

    fun getUserList(since: Int): Single<List<UserListData>> {
        return getApi(api.getUserList(since))
    }

    fun getUserData(userName: String): Single<UserData> {
        return getApi(api.getUserData(userName))
    }

}