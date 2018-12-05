package com.freaky.id.footballscheduleapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayersDetailItem(@SerializedName("strPlayer")
                       var strPlayer: String = "",
                             @SerializedName("idTeam")
                       var idTeam: String? = null,
                             @SerializedName("strDescriptionEN")
                       var strDescriptionEN: String? = null,
                             @SerializedName("strTeam")
                       var strTeam: String? = null,
                             @SerializedName("strFanart1")
                       var strFanart1: String? = null,
                             @SerializedName("strFanart2")
                       var strFanart2: String? = null,
                             @SerializedName("strFanart3")
                       var strFanart3: String? = null,
                             @SerializedName("strFanart4")
                       var strFanart4: String? = null,
                             @SerializedName("strCutout")
                       var strCutOut: String? = null,
                             @SerializedName("idPlayer")
                       var idPlayer: String? = null): Parcelable