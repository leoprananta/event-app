package com.example.event.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.event.R
import com.example.event.views.all.CityFragment
import com.example.event.views.home.HomeFragment
import com.example.event.views.user.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_botNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.home -> {
                    loadHomeFragment(savedInstanceState)
                }

                R.id.allEvent -> {
                    loadEventFragment(savedInstanceState)
                }

                R.id.user -> {
                    loadUserFragment(savedInstanceState)
                }
            }
            true
        }
        main_botNav.selectedItemId = R.id.home
    }

    private fun loadHomeFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, HomeFragment(), HomeFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadEventFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, CityFragment(), CityFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadUserFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, UserFragment(), UserFragment::class.java.simpleName)
                .commit()
        }
    }
}
