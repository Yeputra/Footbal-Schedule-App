package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.model.EventsItem

interface MatchDetailView {
    fun showMatchDetail(data: EventsItem)
}