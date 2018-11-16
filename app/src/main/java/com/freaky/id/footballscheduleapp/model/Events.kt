package com.freaky.id.footballscheduleapp.model

import com.google.gson.annotations.SerializedName

data class Events(@SerializedName("events")
                  var events: List<EventsItem>?)