package com.sunasterisk.youtubebackground.screen.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sunasterisk.youtubebackground.R
import com.sunasterisk.youtubebackground.screen.fragment.colectiontrending.CollectionTrendingFragment
import com.sunasterisk.youtubebackground.screen.fragment.home.HomeFragment
import com.sunasterisk.youtubebackground.screen.fragment.search.SearchFragment
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

        val manage = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.iconSearch)?.actionView as SearchView
        searchView.setSearchableInfo(manage.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {
                    val fragment = SearchFragment.newInstance(it)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameContent, fragment)
                        .addToBackStack(null)
                        .commit()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?) = true
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.iconMenu -> iconMenuOnClick()
        }
        return true
    }

    private fun iconMenuOnClick() {
        linearSideMenu.isVisible = !linearSideMenu.isVisible
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        }
    }

    public fun replaceFragment(view: View) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val fragment: Fragment? = when (view.id) {
            R.id.buttonHome -> HomeFragment.newInstance()
            R.id.buttonTrending -> CollectionTrendingFragment.newInstance()
            else -> null
        }

        fragment?.let {
            fragmentTransaction.replace(R.id.frameContent, it)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}
