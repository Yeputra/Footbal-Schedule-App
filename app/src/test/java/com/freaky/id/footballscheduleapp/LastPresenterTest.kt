package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.LastMatch.LastPresenter
import com.freaky.id.footballscheduleapp.LastMatch.LastView
import com.freaky.id.footballscheduleapp.model.Events
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class LastPresenterTest
 {
     @Mock private lateinit var view: LastView
     @Mock private lateinit var apiRepository: ApiRepository
     @Mock private lateinit var gson: Gson

     @Mock private lateinit var presenter: LastPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter =
            LastPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getEventList() {
        val events: MutableList<EventsItem> = mutableListOf()
        val response = Events(events)

        val idEvent = "4328"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getEventsLast(idEvent)).await(),
                    Events::class.java
                )
            ).thenReturn(response)

            presenter.getEventList(idEvent)

            verify(view).showLoading()
            verify(view).showEventList(events)
            verify(view).hideLoading()
        }

    }
}

