package com.noelvillaman.software.cruiseship.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noelvillaman.software.cruiseship.model.CruiseBliss
import com.noelvillaman.software.cruiseship.networking.CruiseApi.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlissViewModel : ViewModel() {
    val cruiseBlisssList = MutableLiveData<CruiseBliss>()
    val cruiseBlissLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private var cruiseBlissCall: Call<CruiseBliss>? = null

    internal val error: LiveData<Boolean>
        get() = cruiseBlissLoadError

    init {
        fetchCruiseBlisss()
    }

    internal fun getCruiseBlisss(): MutableLiveData<CruiseBliss> {
        return cruiseBlisssList
    }

    internal fun getLoading(): LiveData<Boolean> {
        return loading
    }

    private fun fetchCruiseBlisss() {
        loading.value = true
        cruiseBlissCall = instance?.getBlissInfo()
        cruiseBlissCall!!.enqueue(object : Callback<CruiseBliss> {
            override fun onResponse(
                call: Call<CruiseBliss>,
                response: Response<CruiseBliss>
            ) {
                cruiseBlissLoadError.value = false
                cruiseBlisssList.value = response.body()
                Log.d("BLISS", response.body().toString())
                loading.value = false
                cruiseBlissCall = null
            }

            override fun onFailure(call: Call<CruiseBliss>, t: Throwable) {
                Log.e(javaClass.simpleName, "Error loading Cruise Bliss Info", t)
                cruiseBlissLoadError.value = true
                loading.value = false
                cruiseBlissCall = null
            }
        })
    }

    override fun onCleared() {
        if (cruiseBlissCall != null) {
            cruiseBlissCall!!.cancel()
            cruiseBlissCall = null
        }
    }
}