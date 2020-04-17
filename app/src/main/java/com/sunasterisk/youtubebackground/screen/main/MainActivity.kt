package com.sunasterisk.youtubebackground.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sunasterisk.youtubebackground.R
import com.sunasterisk.youtubebackground.screen.fragment.colectiontrending.ColectionTrendingFragment
import com.sunasterisk.youtubebackground.screen.fragment.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarMain)
        addFirstFragment()
    }

    private fun addFirstFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameContent, HomeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.iconMenu -> iconMenuOnClick()
        }
        return true
    }

    private fun iconMenuOnClick() {
        linearSideMenu.isVisible = !linearSideMenu.isVisible
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1){
            supportFragmentManager.popBackStack()
        }
    }

    public fun replaceFragment(view: View) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val fragment: Fragment? = when (view.id) {
            R.id.buttonHome -> HomeFragment.newInstance()
            R.id.buttonTrending -> ColectionTrendingFragment.newInstance()
            else -> null
        }

        fragment?.let {
            fragmentTransaction.replace(R.id.frameContent, it)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}
