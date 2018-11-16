package com.freaky.id.footballscheduleapp.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freaky.id.footballscheduleapp.API.API
import com.freaky.id.footballscheduleapp.API.RetrofitInterface
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.adapter.EventAdapter
import com.freaky.id.footballscheduleapp.model.Events
import com.freaky.id.footballscheduleapp.model.EventsItem
import kotlinx.android.synthetic.main.fragment_last.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentLast : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_last, container, false)
        var match_recycler_last = rootView.findViewById(R.id.match_recycler_last) as RecyclerView
        match_recycler_last.layoutManager = LinearLayoutManager(context)
        match_recycler_last.adapter = EventAdapter(teams)
        return rootView


        getLastMatch()



    }


    fun getLastMatch() {
        val retrofitInterface : RetrofitInterface = API.getClient().create(RetrofitInterface::class.java)
        val call : Call<Events> = retrofitInterface.getLastmatch("4328")
        call.enqueue(object : Callback<Events> {
            override fun onFailure(call: Call<Events>?, t: Throwable?) {
                Log.d("$TAG", "Gagal Fetch match")
            }

            override fun onResponse(call: Call<Events>?, response: Response<Events>?) {
                Log.d("$TAG", "Team size ${teams.size}")
                match_recycler_last.adapter = EventAdapter(teams)
            }

        })
    }


    companion object {
        private var teams: MutableList<EventsItem> = mutableListOf()

        fun newInstance(): FragmentLast =
            FragmentLast()

    }
}
