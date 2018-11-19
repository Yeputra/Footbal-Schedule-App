package com.freaky.id.footballscheduleapp.model

import com.google.gson.annotations.SerializedName

data class EventsDetailItem(@SerializedName("intHomeShots")
                            var intHomeShots: String? = null,
                            @SerializedName("strSport")
                            var strSport: String? = null,
                            @SerializedName("strHomeLineupDefense")
                            var strHomeLineupDefense: String? = null,
                            @SerializedName("strAwayLineupSubstitutes")
                            var strAwayLineupSubstitutes: String? = null,
                            @SerializedName("idSoccerXML")
                            var idSoccerXML: String? = null,
                            @SerializedName("strHomeLineupForward")
                            var strHomeLineupForward: String? = null,
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
                            var dateEvent: String = "",
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
                            @SerializedName("strBanner")
                            var strBanner: String? = null,
                            @SerializedName("strFanart")
                            var strFanart: String? = null,
                            @SerializedName("strDescriptionEN")
                            var strDescriptionEN: String? = null,
                            @SerializedName("strResult")
                            var strResult: String? = null,
                            @SerializedName("intAwayShots")
                            var intAwayShots: String = "",
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
                            @SerializedName("intSpectators")
                            var intSpectators: String = "",
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
                            var intAwayScore: String = "",
                            @SerializedName("strPoster")
                            var strPoster: String? = null)