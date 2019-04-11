package com.freaky.id.footballscheduleapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.Player.PlayerDetailActivity
import com.freaky.id.footballscheduleapp.model.PlayerItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.startActivity

class PlayerAdapter (private val context: Context, private val player: List<PlayerItem>) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :PlayerAdapter.ViewHolder{
        return PlayerAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false))
    }

    override fun onBindViewHolder(holder: PlayerAdapter.ViewHolder, position: Int) {
        holder.bindItem(player[position])

        holder.itemView.setOnClickListener {
            val item = player[position]
            context.startActivity<PlayerDetailActivity>("player" to item.idPlayer)
        }
    }

    override fun getItemCount(): Int = player.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPlayer : ImageView = itemView.findViewById(R.id.iv_player)
        private val tvPlayer : TextView = itemView.findViewById(R.id.tv_player)

        fun bindItem(player: PlayerItem) {
            tvPlayer.text = player.strPlayer
            Picasso.get()
                .load(player.strCutout)
                .placeholder(R.drawable.player)
                .into(ivPlayer)
        }

    }
}