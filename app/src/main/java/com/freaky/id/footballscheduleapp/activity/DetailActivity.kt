package com.freaky.id.footballscheduleapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.MatchDetailPresenter
import com.freaky.id.footballscheduleapp.MatchDetailView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.R.drawable.ic_add_to_favorite
import com.freaky.id.footballscheduleapp.R.drawable.ic_added_to_favorite
import com.freaky.id.footballscheduleapp.R.id.add_to_favorite
import com.freaky.id.footballscheduleapp.R.menu.detail_menu
import com.freaky.id.footballscheduleapp.db.Favorite
import com.freaky.id.footballscheduleapp.db.database
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.freaky.id.footballscheduleapp.model.TeamList
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
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
    private lateinit var events : EventsItem
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

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
        favoriteState()
        presenter = MatchDetailPresenter(this, request, gson)

        presenter.getMatchDetail(matchId)
        presenter.getTeamDetail(homeId)
        presenter.getTeamDetail(awayId,false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
           add_to_favorite -> {
               if (isFavorite) removeFromFavorite() else addToFavorite()

               isFavorite = !isFavorite
               setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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

        events = EventsItem(data.eventId,
            data.dateEvent,
            data.idHomeTeam,
            data.strHomeTeam,
            data.idAwayTeam,
            data.strAwayTeam)

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

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to events.eventId,
                    Favorite.DATE_EVENT to events.dateEvent,
                    Favorite.TEAM_HOME_ID to events.idHomeTeam,
                    Favorite.TEAM_HOME_NAME to events.strHomeTeam,
                    Favorite.TEAM_AWAY_ID to events.idAwayTeam,
                    Favorite.TEAM_AWAY_NAME to events.strAwayTeam)
            }
            longToast("Added to Favorite")
        } catch (e: SQLiteConstraintException){
            longToast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(TEAM_HOME_ID = {id})",
                    "id" to homeId)
            }
            longToast("Removed From Favorite")
        } catch (e: SQLiteConstraintException){
            longToast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(TEAM_HOME_ID = {id})",
                    "id" to homeId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
