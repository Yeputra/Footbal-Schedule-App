package com.freaky.id.footballscheduleapp.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.model.EventsItem

class EventAdapter(private val events: List<EventsItem>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item_last, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position])

        holder.itemView.setOnClickListener {
            //context.startActivity<DetailActivity>("match" to item)
            Log.d("Test", "Ini adalah list ke")
        }
    }

    override fun getItemCount(): Int = events.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate : TextView = itemView.findViewById(R.id.txtDateMatch)
        var tvTeamA : TextView = itemView.findViewById(R.id.txtTeamNameA)
        var tvScoreTeamA : TextView = itemView.findViewById(R.id.txtTeamScoreA)
        var tvTeamB : TextView = itemView.findViewById(R.id.txtTeamNameB)
        var tvScoreTeamB : TextView = itemView.findViewById(R.id.txtTeamScoreB)

        fun bindItem(events: EventsItem) {
            tvTeamA.text = events.strHomeTeam
            tvScoreTeamA.text = events.intHomeScore
            tvTeamB.text = events.strAwayTeam
            tvScoreTeamB.text = events.intAwayScore
            tvDate.text = events.dateEvent
        }

    }

}