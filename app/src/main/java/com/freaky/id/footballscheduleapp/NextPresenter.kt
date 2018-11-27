package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.model.Events
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextPresenter (private val view: NextView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getEventList(id: String?) {
        view.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getEventsNext(id)).await(),
                Events::class.java
            )

                view.hideLoading()
                view.showEventList(data.events)

        }
    }

}