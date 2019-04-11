package com.freaky.id.footballscheduleapp.SearchTeam

import com.freaky.id.footballscheduleapp.model.TeamsItem

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchTeamList(data: List<TeamsItem>)
}