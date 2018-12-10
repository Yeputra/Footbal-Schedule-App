package com.freaky.id.footballscheduleapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.LastPresenter
import com.freaky.id.footballscheduleapp.LastView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.adapter.EventAdapterLast
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last.*
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh

class FragmentLast : Fragment(), LastView {

    companion object {
        private var events: MutableList<EventsItem> = mutableListOf()
        private lateinit var presenter: LastPresenter
        private lateinit var adapterEventLast: EventAdapterLast
        private lateinit var idEvent : String
        private lateinit var idLeague : String

        fun newInstance(): FragmentLast =
            FragmentLast()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var match_recycler_last = find(R.id.match_recycler_last) as RecyclerView
        match_recycler_last.layoutManager = LinearLayoutManager(activity)
        adapterEventLast = EventAdapterLast(this!!.context!!, events)
        match_recycler_last.adapter = adapterEventLast

        val request = ApiRepository()
        val gson = Gson()
        presenter = LastPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner2.adapter = spinnerAdapter

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                idEvent = spinner2.selectedItem.toString()
                if(idEvent == "English Premier League"){
                    idLeague = "4328"
                }
                else if(idEvent == "English League Championship"){
                    idLeague = "4329"
                }
                else if(idEvent == "German Bundesliga"){
                    idLeague ="4331"
                }
                else if(idEvent == "Italian Serie A"){
                    idLeague = "4332"
                }
                else if(idEvent == "French Ligue 1"){
                    idLeague = "4334"
                }
                else if(idEvent == "Spanish La Liga"){
                    idLeague = "4335"
                }
                else {
                    idLeague = "4328"
                }
                presenter.getEventList(idLeague)
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        swipe.onRefresh {
            presenter.getEventList(idLeague)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_last, container, false)

        return rootView
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showEventList(data: List<EventsItem>) {
        swipe.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapterEventLast.notifyDataSetChanged()
    }

}
