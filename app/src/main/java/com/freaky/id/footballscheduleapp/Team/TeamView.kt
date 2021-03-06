package com.freaky.id.footballscheduleapp.Team

import com.freaky.id.footballscheduleapp.model.TeamList

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamList>)
}