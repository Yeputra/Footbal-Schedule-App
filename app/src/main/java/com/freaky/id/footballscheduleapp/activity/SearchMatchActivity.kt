package com.freaky.id.footballscheduleapp.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.EditText
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.SearchMatchPresenter
import com.freaky.id.footballscheduleapp.SearchMatchView
import com.freaky.id.footballscheduleapp.adapter.EventAdapterLast
import com.freaky.id.footballscheduleapp.adapter.SearchMatchAdapter
import com.freaky.id.footballscheduleapp.fragment.FragmentLast
import com.freaky.id.footballscheduleapp.model.EventItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.find


class SearchMatchActivity : AppCompatActivity(), SearchMatchView {
    private var events: MutableList<EventItem> = mutableListOf()
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var searchMatchAdapter: SearchMatchAdapter
    private lateinit var query : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        initToolbar()
        query = intent.getStringExtra("query")

        var match_recycler_last = find(R.id.rv_search_match) as RecyclerView
        match_recycler_last.layoutManager = LinearLayoutManager(this)
        searchMatchAdapter = SearchMatchAdapter(this, events)
        match_recycler_last.adapter = searchMatchAdapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)
        presenter.getSearchMatch(query)
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
                presenter.getSearchMatch(newText)
                return false
            }
        })

                return true
    }

    override fun showLoading() {
        progress_3.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_3.visibility = View.GONE
    }

    override fun showSearchEventList(data: List<EventItem>) {
        events.clear()
        events.addAll(data)
        searchMatchAdapter.notifyDataSetChanged()
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Search Match"
        val color = resources.getColor(R.color.colorCard)
        toolbar.setTitleTextColor(color)
    }

}
