package com.freaky.id.footballscheduleapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.LastPresenter
import com.freaky.id.footballscheduleapp.LastView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.adapter.EventAdapter
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.google.gson.Gson

class FragmentLast : Fragment(), LastView {

    companion object {
        private var events: MutableList<EventsItem> = mutableListOf()
        private lateinit var presenter: LastPresenter
        private lateinit var adapterEvent: EventAdapter
        private val idEvent : String = "4328"
        private lateinit var progressBar : ProgressBar

        fun newInstance(): FragmentLast =
            FragmentLast()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_last, container, false)
        var match_recycler_last = rootView.findViewById(R.id.match_recycler_last) as RecyclerView
        match_recycler_last.layoutManager = LinearLayoutManager(context)
        adapterEvent = EventAdapter(events)
        match_recycler_last.adapter = adapterEvent

        progressBar = rootView.findViewById(R.id.progressBar) as ProgressBar

        val request = ApiRepository()
        val gson = Gson()
        presenter = LastPresenter(this, request, gson)

        presenter.getEventList(idEvent)
        return rootView
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showEventList(data: List<EventsItem>) {
        events.clear()
        events.addAll(data)
        adapterEvent.notifyDataSetChanged()
    }

}
