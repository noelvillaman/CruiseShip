package com.noelvillaman.software.cruiseship.networking

import com.noelvillaman.software.cruiseship.model.CruiseBliss
import com.noelvillaman.software.cruiseship.model.CruiseScape
import com.noelvillaman.software.cruiseship.model.CruiseSky
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CruiseService {
    @GET("SKY")
    fun getSkyInfo() : Call<CruiseSky>

    @GET("BLISS")
    fun getBlissInfo() : Call<CruiseBliss>

    @GET("ESCAPE")
    fun getCruiseScape(): Call<CruiseScape>

}