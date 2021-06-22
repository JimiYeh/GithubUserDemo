package com.cloudinteractive.githubuserdemo.ui.listPage

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cloudinteractive.githubuserdemo.repository.UserRepository
import kotlinx.coroutines.flow.collectLatest

class UserListPresenter(private val view: UserListContract.View) : UserListContract.Presenter {

    private val flow = Pager(
        PagingConfig(
            pageSize = 20,
            prefetchDistance = 1,
            enablePlaceholders = false,
        )
    ) {
        UserPagingSource(UserRepository())
    }.flow

    override suspend fun initPager() {
        flow.collectLatest {
            view.updatePageList(it)
        }
    }
}