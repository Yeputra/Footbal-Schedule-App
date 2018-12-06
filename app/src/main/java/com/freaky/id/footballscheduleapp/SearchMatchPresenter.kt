package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.model.Events
import com.freaky.id.footballscheduleapp.model.PlayerDetail
import com.freaky.id.footballscheduleapp.model.SearchEvent
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter (private val view: SearchMatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getSearchMatch(query: String) {
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.searchMatch(query)).await(),
                SearchEvent::class.java
            )
            view.hideLoading()
            view.showSearchEventList(data.event)
        }
    }
}