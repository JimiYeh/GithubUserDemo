package com.cloudinteractive.githubuserdemo.ui.detail

import com.cloudinteractive.githubuserdemo.model.user.GetUserByUserNameResp
import com.cloudinteractive.githubuserdemo.network.ApiResponse

class UserDetailContract {
    interface View {
        suspend fun updateDetail(response: ApiResponse<GetUserByUserNameResp>)
    }

    interface Presenter {
        suspend fun getDetail(login: String)
    }
}