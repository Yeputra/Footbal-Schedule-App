package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.model.Events
import com.freaky.id.footballscheduleapp.model.Team
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter (private val view: TeamDetailView,
private val apiRepository: ApiRepository,
private val gson: Gson,
private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                Team::class.java
            )
            view.hideLoading()
            view.showDetailTeam(data.teams.get(0))

        }
    }
}