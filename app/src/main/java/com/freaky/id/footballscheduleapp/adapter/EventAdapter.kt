package com.freaky.id.footballscheduleapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.model.EventsItem
import kotlinx.android.synthetic.main.match_item_last.view.*

class EventAdapter(private val listEvent: List<EventsItem>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item_last, parent, false))

    override fun getItemCount(): Int = listEvent.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listEvent[position]
        holder.tvDate.text = "coba"
        holder.tvTeamA.text = "Persib"
        holder.tvScoreTeamA.text = "3"
        holder.tvTeamB.text = "Persija"
        holder.tvScoreTeamB.text = "2"

        holder.itemView.setOnClickListener {
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate = itemView.txtDateMatch!!
        var tvTeamA = itemView.txtTeamNameA!!
        var tvScoreTeamA = itemView.txtTeamScoreA!!
        var tvTeamB = itemView.txtTeamNameB!!
        var tvScoreTeamB = itemView.txtTeamScoreB!!

    }

}