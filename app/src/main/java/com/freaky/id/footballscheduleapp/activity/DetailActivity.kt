package com.freaky.id.footballscheduleapp.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.MatchDetailPresenter
import com.freaky.id.footballscheduleapp.MatchDetailView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.freaky.id.footballscheduleapp.model.TeamList
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var matchId : String
    private lateinit var homeId : String
    private lateinit var awayId : String
    private lateinit var tvHome : TextView
    private lateinit var tvAway : TextView
    private lateinit var tvdate : TextView
    private lateinit var tvHomeScore : TextView
    private lateinit var tvAwayScore : TextView
    private lateinit var tvHomeGoals : TextView
    private lateinit var tvAwayGoals : TextView
    private lateinit var tvHomeShots : TextView
    private lateinit var tvAwayShots : TextView
    private lateinit var progressBar : ProgressBar
    private lateinit var cardScore : CardView
    private lateinit var cardGoal : CardView
    private lateinit var ivHome : ImageView
    private lateinit var ivAway : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        matchId = intent.getStringExtra("match")
        homeId = intent.getStringExtra("homeTeam")
        awayId = intent.getStringExtra("awayTeam")
        initToolbar()

        progressBar = findViewById(R.id.progressBar2)
        cardScore = findViewById(R.id.cardScore)
        cardGoal = findViewById(R.id.cvGoal)

        tvHome = findViewById(R.id.txtTeamNameA)
        tvAway = findViewById(R.id.txtTeamNameB)
        tvdate = findViewById(R.id.txtDateMatch)
        tvHomeScore = findViewById(R.id.txtTeamScoreA)
        tvAwayScore = findViewById(R.id.txtTeamScoreB)
        tvHomeGoals = findViewById(R.id.tvHomeGoal)
        tvAwayGoals = findViewById(R.id.tvAwayGoal)
        tvHomeShots = findViewById(R.id.tvHomeShot)
        tvAwayShots = findViewById(R.id.tvAwayShot)
        ivHome = findViewById(R.id.ivHome)
        ivAway = findViewById(R.id.ivAway)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)

        presenter.getMatchDetail(matchId)
        presenter.getTeamDetail(homeId)
        presenter.getTeamDetail(awayId,false)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        cardScore.visibility = View.GONE
        cardGoal.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        cardScore.visibility = View.VISIBLE
        cardGoal.visibility = View.VISIBLE
    }

    override fun showMatchDetail(data: EventsItem) {

        tvHome.text = data.strHomeTeam
        tvAway.text = data.strAwayTeam
        tvdate.text = data.dateEvent
        tvHomeScore.text = data.intHomeScore
        tvAwayScore.text = data.intAwayScore
        tvHomeGoals.text = data.strHomeGoalDetail
        tvAwayGoals.text = data.strAwayGoalDetail
        tvHomeShots.text = data.intHomeShots
        tvAwayShots.text = data.intAwayShots

    }

    override fun showDetailTeam(data: TeamList, isHomeTeam : Boolean){
        Picasso.get().load(data.teamBadge).into(if(isHomeTeam) ivHome else ivAway)

    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Football Schedule App"
        toolbar.setTitleTextColor(Color.WHITE)
    }
}
