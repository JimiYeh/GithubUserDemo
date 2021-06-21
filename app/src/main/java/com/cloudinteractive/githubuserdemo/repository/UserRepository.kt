package com.cloudinteractive.githubuserdemo.repository

import com.cloudinteractive.githubuserdemo.model.user.GetUserListResp
import com.cloudinteractive.githubuserdemo.network.ApiResponse
import com.cloudinteractive.githubuserdemo.network.Client
import com.cloudinteractive.githubuserdemo.network.callApi

class UserRepository {

    suspend fun fetchUserData(id: Int?): ApiResponse<GetUserListResp> {
        return callApi { Client.userApiService.getUserList(userId = id)}
    }
}