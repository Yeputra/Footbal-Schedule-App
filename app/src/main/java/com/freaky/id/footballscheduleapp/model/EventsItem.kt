package com.freaky.id.footballscheduleapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsItem(
                      @SerializedName("idEvent")
                      var eventId: String? = null,
                      @SerializedName("dateEvent")
                      var dateEvent: String? = null,
                      @SerializedName("idHomeTeam")
                      var idHomeTeam : String? = null,
                      @SerializedName("strHomeTeam")
                      var strHomeTeam: String? = null,
                      @SerializedName("idAwayTeam")
                      var idAwayTeam : String? = null,
                      @SerializedName("strAwayTeam")
                      var strAwayTeam: String? = null,
                      @SerializedName("intHomeScore")
                      var intHomeScore: String? = null,
                      @SerializedName("intAwayScore")
                      var intAwayScore: String? = null,
                      @SerializedName("strHomeGoalDetails")
                      var strHomeGoalDetail: String? = null,
                      @SerializedName("strAwayGoalDetails")
                      var strAwayGoalDetail: String? = null,
                      @SerializedName("intHomeShots")
                      var intHomeShots : String? = null,
                      @SerializedName("intAwayShots")
                      var intAwayShots : String? = null,
                      @SerializedName("strTeamBadge")
                      var strTeamBadge : String? = null):Parcelable