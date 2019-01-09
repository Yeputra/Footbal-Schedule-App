package com.freaky.id.footballscheduleapp.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.SearchTeamPresenter
import com.freaky.id.footballscheduleapp.SearchTeamView
import com.freaky.id.footballscheduleapp.adapter.SearchTeamAdapter
import com.freaky.id.footballscheduleapp.model.TeamsItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.find

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {

    private var teams: MutableList<TeamsItem> = mutableListOf()
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var searchTeamAdapter: SearchTeamAdapter
    private lateinit var query : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        initToolbar()
        query = intent.getStringExtra("query")

        var match_recycler_last = find(R.id.rv_search_team) as RecyclerView
        match_recycler_last.layoutManager = LinearLayoutManager(this)
        searchTeamAdapter = SearchTeamAdapter(this, teams)
        match_recycler_last.adapter = searchTeamAdapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchTeamPresenter(this, request, gson)
        presenter.getSearchTeam(query)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater?.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.searchMenu)?.actionView as SearchView?


        searchView?.queryHint = "Search matches"

        val hint = searchView?.findViewById<EditText>(android.support.v7.appcompat.R.id.search_src_text)
        if (hint != null) {
            hint.setHintTextColor(Color.GRAY)
        }


        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getSearchTeam(newText)
                return false
            }
        })

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progress_4.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_4.visibility = View.GONE
    }

    override fun showSearchTeamList(data: List<TeamsItem>) {
        teams.clear()
        teams.addAll(data)
        searchTeamAdapter.notifyDataSetChanged()
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "Search Team"
        val color = resources.getColor(R.color.white)
        toolbar.setTitleTextColor(color)
    }

}
