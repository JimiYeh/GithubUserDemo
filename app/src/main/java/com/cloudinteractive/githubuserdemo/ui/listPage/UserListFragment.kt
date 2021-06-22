package com.cloudinteractive.githubuserdemo.ui.listPage

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cloudinteractive.githubuserdemo.R
import com.cloudinteractive.githubuserdemo.databinding.FragmentUserListBinding
import com.cloudinteractive.githubuserdemo.repository.UserRepository
import com.cloudinteractive.githubuserdemo.viewBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserListFragment : Fragment(R.layout.fragment_user_list) {


    private val binding: FragmentUserListBinding by viewBinding(FragmentUserListBinding::bind)

    val flow = Pager(
        PagingConfig(
            pageSize = 20,
            prefetchDistance = 1,
            enablePlaceholders = false,
        )
    ) {
        UserPagingSource(UserRepository())
    }.flow

    @ObsoleteCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyController = UserListEpoxyController(this::onUserItemClick)

        lifecycleScope.launch {
            flow.collectLatest {
                epoxyController.submitData(it)
            }
        }

        epoxyController.addLoadStateListener { loadStates ->
            binding.pbLoading.isVisible = loadStates.refresh is LoadState.Loading
        }

        binding.ervUserList.setController(epoxyController)
    }

    private fun onUserItemClick(login: String) {

    }
}