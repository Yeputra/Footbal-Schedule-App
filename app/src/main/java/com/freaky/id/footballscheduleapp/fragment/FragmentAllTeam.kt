package com.freaky.id.footballscheduleapp.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import org.jetbrains.anko.support.v4.onRefresh
import android.widget.Spinner
import com.freaky.id.footballscheduleapp.*
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.R.array.league

import com.freaky.id.footballscheduleapp.adapter.TeamAdapter
import com.freaky.id.footballscheduleapp.model.TeamList
import com.google.gson.Gson
import org.jetbrains.anko.find

class FragmentAllTeam  : Fragment(), TeamView {

    companion object {
        private var teams: MutableList<TeamList> = mutableListOf()
        private lateinit var presenter: TeamPresenter
        private lateinit var teamAdapter: TeamAdapter
        private lateinit var progressBar : ProgressBar
        private lateinit var spinner: Spinner
        private lateinit var leagueName: String
        private lateinit var swipeRefresh: SwipeRefreshLayout

        fun newInstance(): FragmentAllTeam =
            FragmentAllTeam()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_all_team, container, false)
        var match_recycler_last = rootView.findViewById(R.id.team_recycler) as RecyclerView
        match_recycler_last.layoutManager = LinearLayoutManager(activity)
        teamAdapter = TeamAdapter(this!!.context!!, teams)
        match_recycler_last.adapter = teamAdapter
        swipeRefresh = rootView.findViewById(R.id.swipe) as SwipeRefreshLayout
        spinner = rootView.findViewById(R.id.spinner) as Spinner
        progressBar = rootView.findViewById(R.id.progressBar) as ProgressBar

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }


        return rootView
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamList(data: List<TeamList>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        teamAdapter.notifyDataSetChanged()
    }

}