package com.cloudinteractive.githubuserdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.cloudinteractive.githubuserdemo.R
import com.cloudinteractive.githubuserdemo.ui.listPage.UserListFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            add(R.id.flContainer, UserListFragment(), UserListFragment::class.simpleName)
        }
    }
}