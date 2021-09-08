package com.noelvillaman.software.cruiseship.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noelvillaman.software.cruiseship.model.CruiseBliss
import com.noelvillaman.software.cruiseship.model.CruiseScape
import com.noelvillaman.software.cruiseship.model.CruiseSky
import com.noelvillaman.software.cruiseship.networking.CruiseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkyViewModel : ViewModel() {
    val cruiseSkyObject = MutableLiveData<CruiseSky>()
    val cruiseBlissObject = MutableLiveData<CruiseBliss>()
    val cruiseScapeObject = MutableLiveData<CruiseScape>()

    private var cruiseSkyCall: Call<CruiseSky>? = null
    private var cruiseBlissCall : Call<CruiseBliss>? = null
    private var cruiseScapeCall : Call<CruiseScape>? = null

    init {
        fetchcruiseSky()
        fetchCruiseBliss()
        fetchCruiseScape()
    }

    internal fun getCruiseSky(): LiveData<CruiseSky> {
        return cruiseSkyObject
    }

    internal fun getCruiseBliss() : LiveData<CruiseBliss> {
        return cruiseBlissObject
    }

    internal fun getCruiseScape() : LiveData<CruiseScape> {
        return cruiseScapeObject
    }


    private fun fetchcruiseSky() {
        cruiseSkyCall = CruiseApi.instance?.getSkyInfo()
        cruiseSkyCall?.enqueue(object : Callback<CruiseSky> {
            override fun onResponse(
                call: Call<CruiseSky>,
                response: Response<CruiseSky>
            ) {
                cruiseSkyObject.value = response.body()
                Log.d("SKY", response.body().toString())
                cruiseSkyCall = null
            }

            override fun onFailure(call: Call<CruiseSky>, t: Throwable) {
                Log.e(javaClass.simpleName, "Error loading cruiseScapes", t)
                cruiseSkyCall = null
            }
        })
    }

    private fun fetchCruiseBliss() {
        cruiseBlissCall = CruiseApi.instance?.getBlissInfo()
        cruiseBlissCall?.enqueue(object : Callback<CruiseBliss> {
            override fun onResponse(
                call: Call<CruiseBliss>,
                response: Response<CruiseBliss>
            ) {
                cruiseBlissObject.value = response.body()
                Log.d("SKY", response.body().toString())
                cruiseBlissCall = null
            }

            override fun onFailure(call: Call<CruiseBliss>, t: Throwable) {
                Log.e(javaClass.simpleName, "Error loading cruise bliss", t)
                cruiseBlissCall = null
            }
        })
    }

    private fun fetchCruiseScape() {
        cruiseScapeCall = CruiseApi.instance?.getCruiseScape()
        cruiseScapeCall?.enqueue(object : Callback<CruiseScape> {
            override fun onResponse(
                call: Call<CruiseScape>,
                response: Response<CruiseScape>
            ) {
                cruiseScapeObject.value = response.body()
                Log.d("SKY", response.body().toString())
                cruiseScapeCall = null
            }

            override fun onFailure(call: Call<CruiseScape>, t: Throwable) {
                Log.e(javaClass.simpleName, "Error loading cruiseScapes", t)
                cruiseScapeCall = null
            }
        })
    }

    override fun onCleared() {
        if (cruiseSkyCall != null) {
            cruiseSkyCall?.cancel()
            cruiseSkyCall = null
        }

        if (cruiseBlissCall != null) {
            cruiseBlissCall?.cancel()
            cruiseBlissCall = null
        }

        if (cruiseScapeCall != null) {
            cruiseScapeCall?.cancel()
            cruiseScapeCall = null
        }
    }
}