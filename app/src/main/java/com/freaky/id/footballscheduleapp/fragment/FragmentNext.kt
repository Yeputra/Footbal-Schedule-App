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
import com.freaky.id.footballscheduleapp.NextPresenter
import com.freaky.id.footballscheduleapp.NextView
import com.freaky.id.footballscheduleapp.R
import com.freaky.id.footballscheduleapp.adapter.EventAdapterNext
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.google.gson.Gson

class FragmentNext : Fragment(), NextView {

    companion object {
        private var events: MutableList<EventsItem> = mutableListOf()
        private lateinit var presenter: NextPresenter
        private lateinit var adapterEvent: EventAdapterNext
        private val idEvent : String = "4328"
        private lateinit var progressBar : ProgressBar

        fun newInstance(): FragmentNext =
            FragmentNext()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_next, container, false)
        var match_recycler_next = rootView.findViewById(R.id.match_recycler_next) as RecyclerView
        match_recycler_next.layoutManager = LinearLayoutManager(context)
        adapterEvent = EventAdapterNext(this!!.context!!, events)
        match_recycler_next.adapter = adapterEvent

        progressBar = rootView.findViewById(R.id.progressBar) as ProgressBar

        val request = ApiRepository()
        val gson = Gson()
        presenter = NextPresenter(this, request, gson)

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
