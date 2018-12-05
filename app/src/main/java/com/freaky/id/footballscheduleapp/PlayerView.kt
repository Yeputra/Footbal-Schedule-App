package com.freaky.id.footballscheduleapp

import com.freaky.id.footballscheduleapp.model.PlayersDetailItem

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showDetailPlayer(data: PlayersDetailItem)
}