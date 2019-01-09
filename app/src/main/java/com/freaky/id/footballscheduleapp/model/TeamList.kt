package com.freaky.id.footballscheduleapp.model

import com.google.gson.annotations.SerializedName

data class TeamList (
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDes: String? = null,

    @SerializedName("strTeamFanart1")
    var fanart: String? = null,

    @SerializedName("strAlternate")
    var alt: String? = null,

    @SerializedName("intFormedYear")
    var year: String? = null

)