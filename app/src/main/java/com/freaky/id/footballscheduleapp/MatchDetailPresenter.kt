package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.model.Events
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson
) {
    fun getMatchDetail(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(id)),
                Events::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.events.get(0))
            }
        }
    }
}