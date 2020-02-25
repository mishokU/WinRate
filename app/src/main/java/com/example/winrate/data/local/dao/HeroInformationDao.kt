package com.example.winrate.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.winrate.data.local.database.HeroesDatabase
import com.example.winrate.data.local.models.Hero

@Dao
interface HeroInformationDao {

    @Insert
    fun insert(hero : Hero)

//    @Insert
//    fun insertAll(vararg heroes: HeroesDatabase)

    @Update
    fun update(hero : Hero)

    @Query("Select * from heroes_table where heroId = :key")
    fun getHero(key : Long) : Hero

    @Query("Delete from heroes_table")
    fun clear()

    @Query("Select * from heroes_table order by heroId")
    fun getAllHeroes() : LiveData<List<Hero>>


}