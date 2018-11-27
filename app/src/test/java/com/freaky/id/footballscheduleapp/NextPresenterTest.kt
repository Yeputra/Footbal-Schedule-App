package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.API.ApiRepository
import com.freaky.id.footballscheduleapp.API.TheSportDBApi
import com.freaky.id.footballscheduleapp.model.Events
import com.freaky.id.footballscheduleapp.model.EventsItem
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextPresenterTest {
    @Mock
    private lateinit var view: NextView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var presenter: NextPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getEventList() {
        val events: MutableList<EventsItem> = mutableListOf()
        val response = Events(events)

        val idEvent = "4328"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getEventsNext(idEvent)).await(),
                    Events::class.java
                )
            ).thenReturn(response)

            presenter.getEventList(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(events)
            Mockito.verify(view).hideLoading()
        }

    }
}