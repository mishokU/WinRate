package com.example.winrate.data.remote.openDotaStratz.models

data class HeroStratz(
    val id: Int,
    val displayName: String,
    val language: Language
)

data class Ability(
    val abilityId: Int,
    val slot: Int
)

data class Language(
    val heroId: Int,
    val gameVersionId: Int,
    val languageId: Int,
    val bio: String,
    val hype: String
)

data class Role(
    val level: Int,
    val roleId: Int
)

data class Stat(
    val agilityBase: Double,
    val agilityGain: Double,
    val attackAcquisitionRange: Double,
    val attackAnimationPoint: Double,
    val attackRange: Double,
    val attackRate: Double,
    val attackType: String,
    val cmEnabled: Boolean,
    val complexity: Int,
    val enabled: Boolean,
    val gameVersionId: Int,
    val heroPrimaryAttribute: Int,
    val heroUnlockOrder: Double,
    val hpBarOffset: Double,
    val hpRegen: Double,
    val intelligenceBase: Double,
    val intelligenceGain: Double,
    val moveSpeed: Double,
    val moveTurnRate: Double,
    val mpRegen: Double,
    val newPlayerEnabled: Boolean,
    val primaryAttribute: String,
    val startingArmor: Double,
    val startingDamageMax: Double,
    val startingDamageMin: Double,
    val startingMagicArmor: Double,
    val strengthBase: Double,
    val strengthGain: Double,
    val team: Boolean,
    val visionDaytimeRange: Double,
    val visionNighttimeRange: Double
)

data class Talent(
    val abilityId: Int,
    val gameVersionId: Int,
    val slot: Int
)