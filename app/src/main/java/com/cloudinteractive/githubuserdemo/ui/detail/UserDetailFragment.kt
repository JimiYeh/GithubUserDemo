package com.cloudinteractive.githubuserdemo.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.cloudinteractive.githubuserdemo.R
import com.cloudinteractive.githubuserdemo.databinding.FragmentUserDetailBinding
import com.cloudinteractive.githubuserdemo.model.user.GetUserByUserNameResp
import com.cloudinteractive.githubuserdemo.network.ApiResponse
import com.cloudinteractive.githubuserdemo.network.Client
import com.cloudinteractive.githubuserdemo.network.callApi
import com.cloudinteractive.githubuserdemo.viewBinding
import kotlinx.coroutines.launch

class UserDetailFragment : Fragment(R.layout.fragment_user_detail){

    private val binding: FragmentUserDetailBinding by viewBinding(FragmentUserDetailBinding::bind)
    private val arg: UserDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivClose.setOnClickListener {
            findNavController().popBackStack()
        }


        lifecycleScope.launch {
            val resp = callApi { Client.userApiService.getUserByUserName(arg.login) }
            when (resp) {
                is ApiResponse.ApiSuccess<GetUserByUserNameResp> -> {
                    resp.data?.let { user->
                        Glide.with(binding.ivAvatar)
                            .load(user.avatarUrl)
                            .circleCrop()
                            .into(binding.ivAvatar)

                        binding.tvName.text = user.name
                        binding.tvBio.text = user.bio
                        binding.tvLogin.text = user.login
                        binding.tvSiteAdmin.visibility = if (user.siteAdmin) VISIBLE else GONE
                        binding.tvLocation.text = user.location
                        binding.tvBlog.apply {
                            text = user.blog
                            if (!TextUtils.isEmpty(user.blog))
                                setOnClickListener {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.blog))
                                    startActivity(intent)
                                }
                        }
                    }
                }

                else -> {
                    Toast.makeText(this@UserDetailFragment.context, "API fail", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }
}