package com.freaky.id.footballscheduleapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsItem(
                      @SerializedName("strSport")
                      var strSport: String = "",
                      @SerializedName("strHomeLineupDefense")
                      var strHomeLineupDefense: String = "",
                      @SerializedName("strAwayLineupSubstitutes")
                      var strAwayLineupSubstitutes: String = "",
                      @SerializedName("idLeague")
                      var idLeague: String = "",
                      @SerializedName("idSoccerXML")
                      var idSoccerXML: String = "",
                      @SerializedName("strHomeLineupForward")
                      var strHomeLineupForward: String = "",
                      @SerializedName("strHomeGoalDetails")
                      var strHomeGoalDetails: String = "",
                      @SerializedName("strAwayLineupGoalkeeper")
                      var strAwayLineupGoalkeeper: String = "",
                      @SerializedName("strAwayLineupMidfield")
                      var strAwayLineupMidfield: String = "",
                      @SerializedName("idEvent")
                      var idEvent: String = "",
                      @SerializedName("intRound")
                      var intRound: String = "",
                      @SerializedName("strHomeYellowCards")
                      var strHomeYellowCards: String = "",
                      @SerializedName("idHomeTeam")
                      var idHomeTeam: String = "",
                      @SerializedName("intHomeScore")
                      var intHomeScore: String = "",
                      @SerializedName("dateEvent")
                      var dateEvent: String?,
                      @SerializedName("strAwayTeam")
                      var strAwayTeam: String = "",
                      @SerializedName("strHomeLineupMidfield")
                      var strHomeLineupMidfield: String = "",
                      @SerializedName("strDate")
                      var strDate: String = "",
                      @SerializedName("strHomeFormation")
                      var strHomeFormation: String = "",
                      @SerializedName("idAwayTeam")
                      var idAwayTeam: String = "",
                      @SerializedName("strAwayRedCards")
                      var strAwayRedCards: String = "",
                      @SerializedName("strFilename")
                      var strFilename: String = "",
                      @SerializedName("strTime")
                      var strTime: String = "",
                      @SerializedName("strAwayGoalDetails")
                      var strAwayGoalDetails: String = "",
                      @SerializedName("strAwayLineupForward")
                      var strAwayLineupForward: String = "",
                      @SerializedName("strLocked")
                      var strLocked: String = "",
                      @SerializedName("strSeason")
                      var strSeason: String = "",
                      @SerializedName("strHomeRedCards")
                      var strHomeRedCards: String = "",
                      @SerializedName("strHomeLineupGoalkeeper")
                      var strHomeLineupGoalkeeper: String = "",
                      @SerializedName("strHomeLineupSubstitutes")
                      var strHomeLineupSubstitutes: String = "",
                      @SerializedName("strAwayFormation")
                      var strAwayFormation: String = "",
                      @SerializedName("strEvent")
                      var strEvent: String = "",
                      @SerializedName("strAwayYellowCards")
                      var strAwayYellowCards: String = "",
                      @SerializedName("strAwayLineupDefense")
                      var strAwayLineupDefense: String = "",
                      @SerializedName("strHomeTeam")
                      var strHomeTeam: String = "",
                      @SerializedName("strLeague")
                      var strLeague: String = "",
                      @SerializedName("intAwayScore")
                      var intAwayScore: String = ""):Parcelable