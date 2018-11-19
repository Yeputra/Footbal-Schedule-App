package com.freaky.id.footballscheduleapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsItem(
                      @SerializedName("idEvent")
                      var eventId: String? = null,
                      @SerializedName("intHomeScore")
                      var intHomeScore: String? = null,
                      @SerializedName("dateEvent")
                      var dateEvent: String? = null,
                      @SerializedName("strAwayTeam")
                      var strAwayTeam: String? = null,
                      @SerializedName("strHomeTeam")
                      var strHomeTeam: String? = null,
                      @SerializedName("intAwayScore")
                      var intAwayScore: String? = null):Parcelable