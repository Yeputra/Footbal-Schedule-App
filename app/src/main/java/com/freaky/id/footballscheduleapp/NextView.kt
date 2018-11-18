package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.model.EventsItem

interface NextView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<EventsItem>)
}