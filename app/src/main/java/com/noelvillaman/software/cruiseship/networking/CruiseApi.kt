package com.noelvillaman.software.cruiseship.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CruiseApi {
    private val BASE_URL = "https://www.ncl.com/cms-service/cruise-ships/"

    private var retrofit: Retrofit? = null
    private var cruiseService: CruiseService? = null

    val instance: CruiseService?
        get() {
            if (cruiseService != null) {
                return cruiseService!!
            }
            if (retrofit == null) {
                initializeRetrofit()
            }
            cruiseService = retrofit?.create(CruiseService::class.java)
            return cruiseService
        }

    private fun initializeRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}