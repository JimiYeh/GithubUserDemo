package com.cloudinteractive.githubuserdemo.ui.listPage

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cloudinteractive.githubuserdemo.R
import com.cloudinteractive.githubuserdemo.databinding.FragmentUserListBinding
import com.cloudinteractive.githubuserdemo.model.user.GetUserListResp
import com.cloudinteractive.githubuserdemo.repository.UserRepository
import com.cloudinteractive.githubuserdemo.viewBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserListFragment : Fragment(R.layout.fragment_user_list), UserListContract.View {

    private val binding: FragmentUserListBinding by viewBinding(FragmentUserListBinding::bind)
    private val presenter: UserListContract.Presenter by lazy { UserListPresenter(this) }
    @ObsoleteCoroutinesApi
    private lateinit var epoxyController: UserListEpoxyController


    @ObsoleteCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        epoxyController = UserListEpoxyController(this::onUserItemClick)

        lifecycleScope.launch {
            presenter.initPager()
        }

        epoxyController.addLoadStateListener { loadStates ->
            binding.pbLoading.isVisible = loadStates.refresh is LoadState.Loading
        }

        binding.ervUserList.setController(epoxyController)
    }

    private fun onUserItemClick(login: String) {
        val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(login)
        findNavController().navigate(action)
    }

    @ObsoleteCoroutinesApi
    override suspend fun updatePageList(pagingData: PagingData<GetUserListResp.RespUserListItem>) {
        lifecycleScope.launch {
            epoxyController.submitData(pagingData)
        }
    }
}