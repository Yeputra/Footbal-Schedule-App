package com.freaky.id.footballscheduleapp.model

import com.google.gson.annotations.SerializedName

data class EventsDetail(@SerializedName("events")
                        var events: List<EventsItem>)