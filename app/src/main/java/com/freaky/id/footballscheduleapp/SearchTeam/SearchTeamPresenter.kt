package com.freaky.id.footballscheduleapp.SearchTeam

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.CoroutineContextProvider
import com.freaky.id.footballscheduleapp.model.SearchTeam
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter (private val view: SearchTeamView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getSearchTeam(query: String) {
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchTeam(query)).await(),
                SearchTeam::class.java
            )
            view.hideLoading()
            view.showSearchTeamList(data.teams)
        }
    }
}