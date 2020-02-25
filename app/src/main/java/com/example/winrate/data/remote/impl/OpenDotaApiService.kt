package com.example.winrate.data.remote.impl

import com.example.winrate.data.remote.openDotaApi.models.HeroDuration
import com.example.winrate.data.remote.openDotaApi.models.HeroFullProperty
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDotaApiService {
    @GET("heroStats")
    fun getHeroesAsync(): Deferred<List<HeroProperty>>

    @GET("")
    fun getFullHeroesInformation(): Deferred<List<HeroFullProperty>>

    @GET("heroes")
    fun getHeroDuration(@Query("hero_id" + "/durations") id: Int): Deferred<List<HeroDuration>>

}