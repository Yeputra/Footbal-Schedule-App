package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.model.EventsItem
import com.freaky.id.footballscheduleapp.model.TeamList

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: EventsItem)
    fun showDetailTeam(data: TeamList, isHomeTeam : Boolean)
}