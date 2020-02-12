package com.example.viewwork.worktext.api

import com.example.viewwork.worktext.data.UserData
import com.example.viewwork.worktext.data.UserListData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApi {

    @GET("users")
    fun getUserList(
        @Query("since") since: Int
    ): Single<List<UserListData>>

    @GET("users/{username}")
    fun getUserData(
        @Path("username") userName: String
    ):Single<UserData>

}