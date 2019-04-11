package com.freaky.id.footballscheduleapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.TeamDetail.TeamDetailActivity
import com.freaky.id.footballscheduleapp.model.TeamsItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.startActivity

class SearchTeamAdapter  (private val context: Context, private val teams: List<TeamsItem>) : RecyclerView.Adapter<SearchTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :SearchTeamAdapter.ViewHolder{
        return SearchTeamAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position])

        holder.itemView.setOnClickListener {
            val item = teams[position]
            context.startActivity<TeamDetailActivity>("team" to item.teamId)
        }
    }

    override fun getItemCount(): Int = teams.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivTeam : ImageView = itemView.findViewById(R.id.iv_team)
        private val tvTeam : TextView = itemView.findViewById(R.id.tv_team)

        fun bindItem(teams: TeamsItem) {
            tvTeam.text = teams.teamName
            Picasso.get().load(teams.teamBadge).into(ivTeam)
        }

    }

}