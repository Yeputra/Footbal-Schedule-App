package com.freaky.id.footballscheduleapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.DetailMatch.DetailActivity
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.freaky.id.footballscheduleapp.utils.DateHelper
import org.jetbrains.anko.startActivity

class EventAdapterLast(private val context: Context, private val events: List<EventsItem>) : RecyclerView.Adapter<EventAdapterLast.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :EventAdapterLast.ViewHolder{
        return EventAdapterLast.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item_last, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position])

        holder.itemView.setOnClickListener {
            val item = events[position]
            context.startActivity<DetailActivity>("match" to item.eventId,
                                                 "homeTeam" to item.idHomeTeam,
                                                 "awayTeam" to item.idAwayTeam)
        }
    }

    override fun getItemCount(): Int = events.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate : TextView = itemView.findViewById(R.id.txtDateMatch)
        private val tvTeamA : TextView = itemView.findViewById(R.id.txtTeamNameA)
        private val tvScoreTeamA : TextView = itemView.findViewById(R.id.txtTeamScoreA)
        private val tvTeamB : TextView = itemView.findViewById(R.id.txtTeamNameB)
        private val tvScoreTeamB : TextView = itemView.findViewById(R.id.txtTeamScoreB)

        fun bindItem(events: EventsItem) {
            tvTeamA.text = events.strHomeTeam
            tvScoreTeamA.text = events.intHomeScore
            tvTeamB.text = events.strAwayTeam
            tvScoreTeamB.text = events.intAwayScore
            tvDate.text = events.dateEvent?.let { DateHelper.formatDateToMatch(it) }
        }

    }

}