package com.freaky.id.footballscheduleapp.db

data class FavoriteTeams (val id: Long?,
                          val teamId: String?,
                          val teamName:String?,
                          val teamBanner: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BANNER: String = "TEAM_BANNER"
    }
}