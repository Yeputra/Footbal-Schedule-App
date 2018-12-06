package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.model.TeamsItem

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchTeamList(data: List<TeamsItem>)
}