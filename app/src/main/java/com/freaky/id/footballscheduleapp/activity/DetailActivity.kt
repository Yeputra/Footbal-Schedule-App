package com.freaky.id.footballscheduleapp.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.MatchDetailPresenter
import com.freaky.id.footballscheduleapp.MatchDetailView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.google.gson.Gson

class DetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var matchId : String
    private lateinit var tvHome : TextView
    private lateinit var tvAway : TextView
    private lateinit var tvdate : TextView
    private lateinit var tvHomeScore : TextView
    private lateinit var tvAwayScore : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        matchId = intent.getStringExtra("match")
        initToolbar()

        tvHome = findViewById(R.id.txtTeamNameA)
        tvAway = findViewById(R.id.txtTeamNameB)
        tvdate = findViewById(R.id.txtDateMatch)
        tvHomeScore = findViewById(R.id.txtTeamScoreA)
        tvAwayScore = findViewById(R.id.txtTeamScoreB)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)

        presenter.getMatchDetail(matchId)
    }

    override fun showMatchDetail(data: EventsItem) {

        tvHome.text = data.strHomeTeam
        tvAway.text = data.strAwayTeam
        tvdate.text = data.dateEvent
        tvHomeScore.text = data.intHomeScore
        tvAwayScore.text = data.intAwayScore

    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Football Schedule App"
        toolbar.setTitleTextColor(Color.WHITE)
    }
}
