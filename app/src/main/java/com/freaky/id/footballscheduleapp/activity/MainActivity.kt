package com.freaky.id.footballscheduleapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.Fragment.FragmentMatches
import com.freaky.id.footballscheduleapp.Fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.event -> {
                    loadMatch(savedInstanceState)
                }
                R.id.teams -> {
                    loadTeam(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.event
    }

    private fun loadMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FragmentMatches(), FragmentMatches::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeam(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Football Schedule App"
        val color = resources.getColor(R.color.white)
        toolbar.setTitleTextColor(color)
    }
}
