package com.example.newsfetcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsfetcher.feature.bookmark.ui.BookmarksFragment
import com.example.newsfetcher.feature.mainscreen.MainScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val bnvMenu: BottomNavigationView by lazy { findViewById(R.id.bnvMenu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnvMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemMain -> {
                    changeFragment(MainScreenFragment())
                }
                R.id.itemBookmark -> {
                    changeFragment(BookmarksFragment())
                }
                else -> {}
            }
            true
        }
        bnvMenu.selectedItemId = R.id.itemMain
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}