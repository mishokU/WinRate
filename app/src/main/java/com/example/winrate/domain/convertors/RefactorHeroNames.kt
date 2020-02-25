package com.example.winrate.domain.convertors

import android.widget.ImageView
import com.example.winrate.domain.models.HeroAbilities
import com.example.winrate.domain.helpers.OpenSkillsFile

class RefactorHeroNames {

    companion object {

        private val heroSkillsMap: MutableMap<String, HeroAbilities> = mutableMapOf()

        fun getUrlSkill(imageView: ImageView, heroName: String, skillNumber: Skills): String? {

            val name = refactorName(heroName)

            if(heroSkillsMap.containsKey(name)) {
               return heroSkillsMap[name]?.getAbility(skillNumber)
            } else {

                val tmpHeroSkillsList = mutableListOf<String>()

                heroSkillsMap.plus(Pair(name,
                    HeroAbilities(
                        tmpHeroSkillsList
                    )
                ))

                for (skill in OpenSkillsFile.readSkillsFile(imageView.context)) {
                    if (containName(skill, name)) {
                        tmpHeroSkillsList.add(skill)
                    }
                }

                heroSkillsMap[name] =
                    HeroAbilities(
                        tmpHeroSkillsList
                    )

                return if (tmpHeroSkillsList.size <= skillNumber.ordinal) {
                    null
                } else {
                    tmpHeroSkillsList[skillNumber.ordinal]
                }
            }
        }

        private fun containName(skill: String, name: String): Boolean {
            val words = skill.split("_")
            if (name == words[0]) {
                return true
            } else if(name == (words[0] + "_" + words[1])) {
                return true
            }
            return false
        }

        private fun refactorName(name: String): String {
            val tmpname = name.replace("npc_dota_hero_", "")
            if (tmpname == "sand_king") {
                return "sandking"
            }
            return tmpname
        }
    }
}