package com.freaky.id.footballscheduleapp.LastMatch

import com.freaky.id.footballscheduleapp.model.EventsItem

interface LastView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<EventsItem>)
}