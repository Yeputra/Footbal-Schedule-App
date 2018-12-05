package com.freaky.id.footballscheduleapp.model

import com.google.gson.annotations.SerializedName

data class PlayerItem(@SerializedName("strPlayer")
                      var strPlayer: String = "",
                      @SerializedName("idTeam")
                      var idTeam: String = "",
                      @SerializedName("strDescriptionEN")
                      var strDescriptionEN: String = "",
                      @SerializedName("strCutout")
                      var strCutout: String = "",
                      @SerializedName("strTeam")
                      var strTeam: String = "",
                      @SerializedName("strFanart1")
                      var strFanart1: String = "",
                      @SerializedName("strFanart2")
                      var strFanart2: String = "",
                      @SerializedName("strFanart3")
                      var strFanart3: String = "",
                      @SerializedName("strFanart4")
                      var strFanart: String = "",
                      @SerializedName("idPlayer")
                      var idPlayer: String = "")