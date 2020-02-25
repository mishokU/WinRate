package com.example.winrate.data.remote.impl

import com.example.winrate.data.remote.openDotaStratz.models.HeroStratz
import retrofit2.Call
import retrofit2.http.GET

interface OpenDotaStratzApiService {

    @GET("Hero")
    fun getHeroesBio() : Call<MutableMap<String, HeroStratz>>

}