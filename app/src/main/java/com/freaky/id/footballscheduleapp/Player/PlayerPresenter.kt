package com.freaky.id.footballscheduleapp.Player

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.CoroutineContextProvider
import com.freaky.id.footballscheduleapp.model.PlayerDetail
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter (private val view: PlayerView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson,
                       private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPlayerDetail(playerId: String) {
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayerDetail(playerId)).await(),
                PlayerDetail::class.java
            )
            view.hideLoading()
            view.showDetailPlayer(data.players.get(0))
        }
    }
}