package com.freaky.id.footballscheduleapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.activity.TeamDetailActivity
import com.freaky.id.footballscheduleapp.db.FavoriteTeams
import com.squareup.picasso.Picasso
import org.jetbrains.anko.startActivity

class FavoriteTeamsAdapter (private val context: Context, private val favorite: List<FavoriteTeams>) : RecyclerView.Adapter<FavoriteTeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :FavoriteTeamsAdapter.ViewHolder{
        return FavoriteTeamsAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position])

        holder.itemView.setOnClickListener {
            val item = favorite[position]
            context.startActivity<TeamDetailActivity>("team" to item.teamId)
        }
    }

    override fun getItemCount(): Int = favorite.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivTeam : ImageView = itemView.findViewById(R.id.iv_team)
        private val tvTeam : TextView = itemView.findViewById(R.id.tv_team)

        fun bindItem(favorite: FavoriteTeams) {
            tvTeam.text = favorite.teamName
            Picasso.get().load(favorite.teamBanner).into(ivTeam)
        }

    }

}