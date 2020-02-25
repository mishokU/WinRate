package com.example.winrate.data.remote.openDotaApi

import com.example.winrate.data.remote.impl.OpenDotaApiService
import com.example.winrate.data.remote.retrofit.retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object HeroesApi {
    val retrofitService : OpenDotaApiService by lazy {
        retrofit.create(
            OpenDotaApiService::class.java)
    }
}