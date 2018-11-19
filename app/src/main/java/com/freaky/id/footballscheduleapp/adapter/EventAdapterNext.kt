package com.freaky.id.footballscheduleapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.activity.DetailActivity
import com.freaky.id.footballscheduleapp.model.EventsItem
import org.jetbrains.anko.startActivity

class EventAdapterNext (private val context: Context, private val events: List<EventsItem>) : RecyclerView.Adapter<EventAdapterNext.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :EventAdapterNext.ViewHolder{
        return EventAdapterNext.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item_next, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position])

        holder.itemView.setOnClickListener {
            val item = events[position]
            context.startActivity<DetailActivity>("match" to item.eventId)
        }
    }

    override fun getItemCount(): Int = events.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate : TextView = itemView.findViewById(R.id.txtDateMatch)
        private val tvTeamA : TextView = itemView.findViewById(R.id.txtTeamNameA)
        private val tvTeamB : TextView = itemView.findViewById(R.id.txtTeamNameB)

        fun bindItem(events: EventsItem) {
            tvTeamA.text = events.strHomeTeam
            tvTeamB.text = events.strAwayTeam
            tvDate.text = events.dateEvent
        }

    }

}