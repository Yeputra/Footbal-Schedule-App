package com.freaky.id.footballscheduleapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.adapter.FavoriteTeamsAdapter
import com.freaky.id.footballscheduleapp.db.Favorite
import com.freaky.id.footballscheduleapp.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FragmentFavorite : Fragment() {


    companion object {

        private var favorites: MutableList<Favorite> = mutableListOf()
        private lateinit var adapterFavorite: FavoriteTeamsAdapter
        fun newInstance(): FragmentFavorite =
            FragmentFavorite()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        var recyclerFavorite = rootView.findViewById(R.id.match_recycler_favorite) as RecyclerView
        recyclerFavorite.layoutManager = LinearLayoutManager(activity)
        adapterFavorite = FavoriteTeamsAdapter(this!!.context!!, favorites)
        recyclerFavorite.adapter = adapterFavorite
        showFavorite()

        return rootView
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapterFavorite.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}
