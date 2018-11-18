package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.model.EventsItem

interface LastView {
    fun showEventList(data: List<EventsItem>)
}