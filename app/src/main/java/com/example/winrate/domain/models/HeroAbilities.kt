package com.example.winrate.domain.models

import com.example.winrate.domain.convertors.Skills

class HeroAbilities(tmpHeroSkillsList: MutableList<String>) {
    private val heroSkills = tmpHeroSkillsList

    fun getAbility(skill : Skills) : String? {
        if(skill.ordinal >= heroSkills.size) {
            return null
        }
        return heroSkills[skill.ordinal]
    }

    fun getAllAbilities() : List<String> {
        return heroSkills.toList()
    }
}
