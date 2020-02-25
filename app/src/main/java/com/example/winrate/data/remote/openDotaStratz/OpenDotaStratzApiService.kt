package com.example.winrate.data.remote.openDotaStratz

import com.example.winrate.data.remote.impl.OpenDotaStratzApiService
import com.example.winrate.data.remote.retrofit.retrofitStratz
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshiStr = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object HeroStratzApi {
    val retrofitService: OpenDotaStratzApiService by lazy {
        retrofitStratz.create(OpenDotaStratzApiService::class.java)
    }
}