package com.cloudinteractive.githubuserdemo.ui.listPage

import android.view.View.GONE
import android.view.View.VISIBLE
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.bumptech.glide.Glide
import com.cloudinteractive.githubuserdemo.R
import com.cloudinteractive.githubuserdemo.databinding.ItemUserListBinding
import com.cloudinteractive.githubuserdemo.epoxy.ViewBindingKotlinModel
import com.cloudinteractive.githubuserdemo.model.user.GetUserListResp
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class UserListEpoxyController(private val onItemClick : (String) -> Unit) : PagingDataEpoxyController<GetUserListResp.RespUserListItem>() {
    override fun buildItemModel(
        currentPosition: Int,
        item: GetUserListResp.RespUserListItem?
    ): EpoxyModel<*> {
        return UserListItemEpoxyModel(
            user = item!!,
            onClick = onItemClick
        ).id(item.id)
    }

//    override fun addModels(models: List<EpoxyModel<*>>) {
//        super.addModels(models)
//    }


    class UserListItemEpoxyModel(
        private val user: GetUserListResp.RespUserListItem,
        private val onClick: (String) -> Unit
    ) : ViewBindingKotlinModel<ItemUserListBinding>(R.layout.item_user_list) {

        override fun ItemUserListBinding.bind() {
            tvLogin.text = user.login
            Glide.with(ivAvatar)
                .load(user.avatarUrl)
                .into(ivAvatar)

            tvSiteAdmin.visibility = if (user.siteAdmin) VISIBLE else GONE
        }
    }
}

