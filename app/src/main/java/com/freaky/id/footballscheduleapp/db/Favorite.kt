package com.freaky.id.footballscheduleapp.db

data class Favorite(val id: Long?,
                    val matchId: String?,
                    val dateEvent:String?,
                    val teamHomeId: String?,
                    val teamHomeName: String?,
                    val teamAwayId: String?,
                    val teamAwayName: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
        const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
    }
}