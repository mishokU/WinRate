package com.example.winrate.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.winrate.data.local.dao.HeroInformationDao
import com.example.winrate.data.local.models.Hero

@Database(entities = [Hero::class], version = 1, exportSchema = false)
abstract class HeroesDatabase : RoomDatabase() {

    abstract val heroInformationDao : HeroInformationDao

    companion object {

        @Volatile
        private var INSTANCE : HeroesDatabase ?= null

        fun getInstance(context : Context) : HeroesDatabase {
            synchronized(this) {
                var instance  = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HeroesDatabase::class.java,
                        "heroes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }



}