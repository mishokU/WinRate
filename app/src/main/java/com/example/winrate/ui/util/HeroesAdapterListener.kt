package com.example.winrate.ui.util

import com.example.winrate.data.remote.openDotaApi.models.HeroProperty

class HeroesAdapterListener(val clickListener: (heroProperty: HeroProperty) -> Unit){
      fun onClick(hero: HeroProperty) = clickListener(hero)
}