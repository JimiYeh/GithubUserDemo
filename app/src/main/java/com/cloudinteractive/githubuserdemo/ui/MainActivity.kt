package com.cloudinteractive.githubuserdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.cloudinteractive.githubuserdemo.R
import com.cloudinteractive.githubuserdemo.ui.listPage.UserListFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navContainer) as NavHostFragment
        val navController = navHostFragment.navController

//        supportFragmentManager.commit {
//            add(R.id.flContainer, UserListFragment(), UserListFragment::class.simpleName)
//        }
    }
}