package com.example.winrate.data.remote.openDotaApi.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroProperty(
    val id: Int,
    val name: String,
    val localized_name: String,
    val primary_attr: String,
    val attack_type: String,
    val roles: List<String>,
    val img: String,
    val icon: String,

    val base_health: Int,
    val base_health_regen: Double? = 0.0,
    val base_mana: Int,
    val base_mana_regen: Double,

    val base_armor: Double = 0.0,
    val base_mr: Int,

    val base_attack_min: Int = 0,
    val base_attack_max: Int = 0,


    val base_str: Int,
    val base_agi: Int,
    val base_int: Int,


    val str_gain: Double,
    val agi_gain: Double,
    val int_gain: Double,

    val attack_range: Int,
    val move_speed: Int,
    val turn_rate: Double,
    val cm_enabled: Boolean,

    val legs: Int
) : Parcelable