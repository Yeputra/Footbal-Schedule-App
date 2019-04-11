package com.freaky.id.footballscheduleapp.TeamDetail

import com.freaky.id.footballscheduleapp.model.PlayerItem
import com.freaky.id.footballscheduleapp.model.TeamList

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailTeam(data: TeamList)
    fun showPlayerList(data: List<PlayerItem>)
}