package com.freaky.id.footballscheduleapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.activity.DetailActivity
import com.freaky.id.footballscheduleapp.db.Favorite
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class FavoriteTeamsAdapter (private val context: Context, private val favorite: List<Favorite>) : RecyclerView.Adapter<FavoriteTeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :FavoriteTeamsAdapter.ViewHolder{
        return FavoriteTeamsAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item_favorite, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position])

        holder.itemView.setOnClickListener {
            val item = favorite[position]
            context.startActivity<DetailActivity>("match" to item.matchId,
                "homeTeam" to item.teamHomeId,
                "awayTeam" to item.teamAwayId)
        }
    }

    override fun getItemCount(): Int = favorite.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate : TextView = itemView.find(R.id.txtDateMatch)
        private val tvTeamA : TextView = itemView.find(R.id.txtTeamNameA)
        private val tvScoreA : TextView = itemView.find(R.id.txtTeamScoreA)
        private val tvTeamB : TextView = itemView.find(R.id.txtTeamNameB)
        private val tvscoreB : TextView = itemView.find(R.id.txtTeamScoreB)

        fun bindItem(favorite: Favorite) {
            tvTeamA.text = favorite.teamHomeName
            tvTeamB.text = favorite.teamAwayName
            tvDate.text = favorite.dateEvent
            if(favorite.teamHomeScore!=null && favorite.teamAwayScore!=null){
                tvScoreA.text = favorite.teamHomeScore
                tvscoreB.text = favorite.teamAwayScore
            }
            else {
                tvScoreA.text = ""
                tvscoreB.text = ""
            }
        }

    }

}