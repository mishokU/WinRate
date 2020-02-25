package com.example.winrate.ui.util

import com.example.winrate.domain.models.HeroRoles

class HeroRolesListener(val clickListener: (heroProperty: HeroRoles) -> Unit){
    fun onClick(hero: HeroRoles) = clickListener(hero)
}