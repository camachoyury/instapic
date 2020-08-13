package com.camachoyury.instapic.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.camachoyury.instapic.R
import com.camachoyury.instapic.databinding.ActivityHomeBinding
import com.camachoyury.instapic.login.LoginActivity
import com.camachoyury.instapic.post.CameraPreviewActivity
import com.camachoyury.instapic.share.ShareActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity: AppCompatActivity() {
    private lateinit var binding :ActivityHomeBinding

    private  val homeViewModel: HomeViewModel by viewModels()
    companion object{

        var currentPic: Uri? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()

        homeViewModel.isLogin.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })

        val host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val nav = host.navController
        binding.bottomNavigationView.let { NavigationUI.setupWithNavController(it,nav) }


        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_post -> {
                    startActivity(Intent(this@HomeActivity, ShareActivity::class.java))

                }
            }
            true
        }
    }

    private fun setUpNavigation() {

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            binding.bottomNavigationView,
            navHostFragment!!.navController
        )
    }
}

