package com.freaky.id.footballscheduleapp.Player

import com.freaky.id.footballscheduleapp.model.PlayersDetailItem

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showDetailPlayer(data: PlayersDetailItem)
}