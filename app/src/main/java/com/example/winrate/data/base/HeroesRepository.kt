package com.example.winrate.data.base

import com.example.winrate.data.local.database.HeroesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeroesRepository(private val database: HeroesDatabase){

    suspend fun refreshHeroes(){
        withContext(Dispatchers.IO){
            
        }
    }
}