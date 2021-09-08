package com.noelvillaman.software.cruiseship.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noelvillaman.software.cruiseship.model.CruiseScape
import com.noelvillaman.software.cruiseship.networking.CruiseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScapeViewModel : ViewModel() {
    val cruiseScapeList = MutableLiveData<CruiseScape>()
    val cruiseScapeLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private var cruiseScapeCall: Call<CruiseScape>? = null

    internal val error: LiveData<Boolean>
        get() = cruiseScapeLoadError

    init {
        fetchcruiseScapes()
    }

    internal fun getcruiseScapes(): LiveData<CruiseScape> {
        return cruiseScapeList
    }

    internal fun getLoading(): LiveData<Boolean> {
        return loading
    }

    private fun fetchcruiseScapes() {
        loading.value = true
        cruiseScapeCall = CruiseApi.instance?.getCruiseScape()
        cruiseScapeCall!!.enqueue(object : Callback<CruiseScape> {
            override fun onResponse(
                call: Call<CruiseScape>,
                response: Response<CruiseScape>
            ) {
                cruiseScapeLoadError.value = false
                cruiseScapeList.value = response.body()
                Log.d("SCAPE", response.body().toString())
                loading.value = false
                cruiseScapeCall = null
            }

            override fun onFailure(call: Call<CruiseScape>, t: Throwable) {
                Log.e(javaClass.simpleName, "Error loading cruiseScapes", t)
                cruiseScapeLoadError.value = true
                loading.value = false
                cruiseScapeCall = null
            }
        })
    }

    override fun onCleared() {
        if (cruiseScapeCall != null) {
            cruiseScapeCall!!.cancel()
            cruiseScapeCall = null
        }
    }
}