package com.freaky.id.footballscheduleapp.Team

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.CoroutineContextProvider
import com.freaky.id.footballscheduleapp.model.Team
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter (private val view: TeamView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamList(league: String?) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)).await(),
                Team::class.java
            )

                view.hideLoading()
                view.showTeamList(data.teams)

        }
    }

}