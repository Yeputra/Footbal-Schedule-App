package com.freaky.id.footballscheduleapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.PlayerPresenter
import com.freaky.id.footballscheduleapp.PlayerView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.model.PlayersDetailItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import org.jetbrains.anko.displayMetrics
import org.jetbrains.anko.find

class PlayerDetailActivity : AppCompatActivity(), PlayerView {

    private lateinit var presenter: PlayerPresenter
    private lateinit var playerID: String
    private lateinit var playerName: String
    private lateinit var ivProfile: ImageView
    private lateinit var ivPoster: ImageView
    private lateinit var tvDes: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        playerID = intent.getStringExtra("player")
        ivProfile = findViewById(R.id.iv_poster)
        ivPoster = findViewById(R.id.iv_poster)
        tvDes = findViewById(R.id.tv_player_des)
        progressBar = findViewById(R.id.progressBar)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayerDetail(playerID)

    }
    override fun showLoading() {
        ivPoster.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        ivProfile.visibility = View.GONE
    }

    override fun hideLoading() {
        ivPoster.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        ivProfile.visibility = View.VISIBLE
    }

    override fun showDetailPlayer(data: PlayersDetailItem) {
        tvDes.text = data.strDescriptionEN
        playerName = data.strPlayer

            Picasso.get()
                .load(data.strFanart1)
                .fit()
                .centerCrop()
                .into(ivPoster)

        initToolbar()
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

   private fun initToolbar() {
        val toolbar: Toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = playerName
       val color = resources.getColor(R.color.white)
        toolbar.setTitleTextColor(color)
    }
}
