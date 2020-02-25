package com.example.winrate.data.remote.retrofit

import com.example.winrate.data.remote.openDotaApi.moshi
import com.example.winrate.data.remote.openDotaStratz.moshiStr
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://api.opendota.com/api/"
private const val BASE_URL_STRATZ="https://api.stratz.com/api/v1/"

val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

val retrofitStratz = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshiStr))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL_STRATZ)
    .build()

