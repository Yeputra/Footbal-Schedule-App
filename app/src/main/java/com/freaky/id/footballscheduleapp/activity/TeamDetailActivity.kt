package com.freaky.id.footballscheduleapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.MatchDetailPresenter
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.R.id.*
import com.freaky.id.footballscheduleapp.TeamDetailPresenter
import com.freaky.id.footballscheduleapp.TeamDetailView
import com.freaky.id.footballscheduleapp.adapter.PlayerAdapter
import com.freaky.id.footballscheduleapp.adapter.TeamAdapter
import com.freaky.id.footballscheduleapp.fragment.FragmentAllTeam
import com.freaky.id.footballscheduleapp.model.PlayerItem
import com.freaky.id.footballscheduleapp.model.TeamList
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private var player: MutableList<PlayerItem> = mutableListOf()
    private lateinit var presenter: TeamDetailPresenter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        initToolbar()

        teamID = intent.getStringExtra("team")

        rvPlayer = find(rv_player)
        playerAdapter = PlayerAdapter(this, player)
        rvPlayer.adapter = playerAdapter
        rvPlayer.layoutManager = LinearLayoutManager(this)

        ivTeam = find(iv_team)
        tvTeam = find(tv_team)
        tvDes = find(tv_des)
        progressBar = find(progressBar2)
        cvTeam = find(cv_team)
        cvDes = find(cv_des)
        cvPlayer = find(cv_player)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)

        presenter.getTeamDetail(teamID)
        presenter.getPlayerList(teamID)
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
}
