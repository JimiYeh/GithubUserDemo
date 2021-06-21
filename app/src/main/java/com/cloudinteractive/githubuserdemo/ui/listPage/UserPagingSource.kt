package com.cloudinteractive.githubuserdemo.ui.listPage

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cloudinteractive.githubuserdemo.model.user.GetUserListResp
import com.cloudinteractive.githubuserdemo.network.ApiResponse
import com.cloudinteractive.githubuserdemo.repository.UserRepository
import java.lang.RuntimeException

class UserPagingSource(private val repository: UserRepository) :
    PagingSource<Int, GetUserListResp.RespUserListItem>() {


    override fun getRefreshKey(state: PagingState<Int, GetUserListResp.RespUserListItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetUserListResp.RespUserListItem> {

        val userId = params.key

        val resp = repository.fetchUserData(userId)
        when (resp) {
            is ApiResponse.ApiSuccess<GetUserListResp> -> {

                if (resp.data!!.isEmpty()) {
                    return LoadResult.Page(
                        data = listOf(),
                        prevKey = null,
                        nextKey = null
                    )
                } else {
                    return LoadResult.Page(
                        data = resp.data,
                        prevKey = null,
                        nextKey = resp.data[resp.data.size-1].id
                    )
                }
            }

            is ApiResponse.ApiError -> {
                return LoadResult.Error(
                    RuntimeException("Api failed")
                )
            }

            is ApiResponse.ApiException -> {
                return LoadResult.Error(resp.exception)
            }
        }
    }

}