package com.freaky.id.footballscheduleapp.API

import android.net.Uri
import com.freaky.id.footballscheduleapp.BuildConfig

object TheSportDBApi {
    fun getEvents(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()
    }
}