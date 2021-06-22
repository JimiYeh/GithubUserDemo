package com.cloudinteractive.githubuserdemo.ui.detail

import com.cloudinteractive.githubuserdemo.network.Client
import com.cloudinteractive.githubuserdemo.network.callApi

class UserDetailPresenter(private val view: UserDetailContract.View) :
    UserDetailContract.Presenter {

    override suspend fun getDetail(login: String) {
        val resp = callApi { Client.userApiService.getUserByUserName(login) }

        view.updateDetail(resp)
    }
}