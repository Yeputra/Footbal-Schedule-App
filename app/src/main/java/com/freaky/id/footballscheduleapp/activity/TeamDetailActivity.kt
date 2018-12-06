package com.freaky.id.footballscheduleapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.R.id.*
import com.freaky.id.footballscheduleapp.TeamDetailPresenter
import com.freaky.id.footballscheduleapp.TeamDetailView
import com.freaky.id.footballscheduleapp.adapter.PlayerAdapter
import com.freaky.id.footballscheduleapp.db.Favorite
import com.freaky.id.footballscheduleapp.db.FavoriteTeams
import com.freaky.id.footballscheduleapp.db.database
import com.freaky.id.footballscheduleapp.model.PlayerItem
import com.freaky.id.footballscheduleapp.model.PlayersDetailItem
import com.freaky.id.footballscheduleapp.model.TeamList
import com.freaky.id.footballscheduleapp.utils.DateHelper
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private var player: MutableList<PlayerItem> = mutableListOf()
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var teamID: String
    private lateinit var ivTeam: ImageView
    private lateinit var tvTeam: TextView
    private lateinit var tvDes: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var cvTeam: CardView
    private lateinit var cvDes: CardView
    private lateinit var cvPlayer: CardView
    private lateinit var rvPlayer: RecyclerView
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var teams: TeamList
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        initToolbar()

        teamID = intent.getStringExtra("team")

        rvPlayer = find(rv_player)
        playerAdapter = PlayerAdapter(this, player)
        rvPlayer.adapter = playerAdapter
        rvPlayer.layoutManager = LinearLayoutManager(this)

        coordinatorLayout = find(R.id.main_content)

        ivTeam = find(iv_team)
        tvTeam = find(tv_team)
        tvDes = find(tv_des)
        progressBar = find(progressBar2)
        cvTeam = find(cv_team)
        cvDes = find(cv_des)
        cvPlayer = find(cv_player)


        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)

        presenter.getTeamDetail(teamID)
        presenter.getPlayerList(teamID)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
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
        cvTeam.visibility = View.GONE
        cvDes.visibility = View.GONE
        cvPlayer.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        cvTeam.visibility = View.VISIBLE
        cvDes.visibility = View.VISIBLE
        cvPlayer.visibility = View.VISIBLE
    }

    override fun showDetailTeam(data: TeamList){

        teams = TeamList(data.teamId,
                        data.teamName,
                        data.teamName)

        tvTeam.text = data.teamName
        Picasso.get().load(data.teamBadge).into(ivTeam)
        tvDes.text = data.teamDes

    }

    override fun showPlayerList(data: List<PlayerItem>){
        player.clear()
        player.addAll(data)
        playerAdapter.notifyDataSetChanged()
    }

    private fun initToolbar() {
        val toolbar: Toolbar = find(toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Football Schedule App"
        val color = resources.getColor(R.color.colorCard)
        toolbar.setTitleTextColor(color)
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    FavoriteTeams.TABLE_FAVORITE,
                    FavoriteTeams.TEAM_ID to teams.teamId,
                    FavoriteTeams.TEAM_NAME to teams.teamName,
                    FavoriteTeams.TEAM_BANNER to teams.teamBadge)
            }
            coordinatorLayout.snackbar("Team Added to Favorite").show()
        } catch (e: SQLiteConstraintException){
            longToast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeams.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to teamID)
            }
            coordinatorLayout.snackbar("Teams Removed to Favorite").show()
        } catch (e: SQLiteConstraintException){
            longToast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeams.TABLE_FAVORITE)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to teamID)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }


}
