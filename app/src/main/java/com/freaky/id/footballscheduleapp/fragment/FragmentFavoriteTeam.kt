package com.freaky.id.footballscheduleapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.adapter.FavoriteTeamsAdapter
import com.freaky.id.footballscheduleapp.db.FavoriteTeams
import com.freaky.id.footballscheduleapp.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FragmentFavoriteTeam : Fragment() {

    companion object {

        private var favorites: MutableList<FavoriteTeams> = mutableListOf()
        private lateinit var adapterFavorite: FavoriteTeamsAdapter
        private lateinit var noData: TextView
        private lateinit var recyclerFavorite: RecyclerView
        fun newInstance(): FragmentFavoriteTeam =
            FragmentFavoriteTeam()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        recyclerFavorite = rootView.findViewById(R.id.rv_favorite_team)
        noData = rootView.findViewById(R.id.txtNoData)
        noData.visibility = View.GONE
        recyclerFavorite.layoutManager = LinearLayoutManager(activity)
        adapterFavorite = FavoriteTeamsAdapter(this!!.context!!, favorites)
        recyclerFavorite.adapter = adapterFavorite
        showFavorite()

        return rootView
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            val result = select(FavoriteTeams.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            favorites.addAll(favorite)
            adapterFavorite.notifyDataSetChanged()
        }
        if (favorites.isEmpty()) {
            recyclerFavorite.visibility = View.GONE
            noData.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}