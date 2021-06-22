package com.cloudinteractive.githubuserdemo.ui.listPage

import androidx.paging.PagingData
import com.cloudinteractive.githubuserdemo.model.user.GetUserListResp

class UserListContract {
    interface View {
        suspend fun updatePageList(pagingData: PagingData<GetUserListResp.RespUserListItem>)
    }

    interface Presenter {
        suspend fun initPager()
    }
}