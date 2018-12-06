package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.model.EventItem

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchEventList(data: List<EventItem>)
}