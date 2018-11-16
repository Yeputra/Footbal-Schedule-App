package com.freaky.id.footballscheduleapp.API

import com.freaky.id.footballscheduleapp.model.Events
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id:String) : Call<Events>

    @GET("eventsnextleague.php")
    fun getUpcomingMatch(@Query("id") id:String) : Call<Events>

}