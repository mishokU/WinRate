package com.example.winrate.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "heroes_table")
data class Hero(

    @PrimaryKey(autoGenerate = true)
    var heroId : Long = 0L,

    val name : String = "NULL",
    val primary_attr : String = "NULL",
    val attack_type : String = "NULL",
    val games_played : Int = 100,
    val attributes: String = "NULL",
    val wins : Int = 100,
    val img : String = "NULL"
)