package com.cloudinteractive.githubuserdemo.network

import com.cloudinteractive.githubuserdemo.model.user.GetUserByUserNameResp
import com.cloudinteractive.githubuserdemo.model.user.GetUserListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    // get user list
    @GET("/users")
    suspend fun getUserList(
        // A user ID. Only return users with an ID greater than this ID.
        @Query("since") userId: Int? = null,

        // Results per page (max 100).   作業要求預設20
        @Query("per_page") pageSize: Int = 20,
    ): Response<GetUserListResp>


    // get a user detail
    @GET("/users/{username}")
    suspend fun getUserByUserName(
        @Path("username") username: String
    ): Response<GetUserByUserNameResp>
}