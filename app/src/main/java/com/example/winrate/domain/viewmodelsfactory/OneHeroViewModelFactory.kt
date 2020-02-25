package com.example.winrate.domain.viewmodelsfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import com.example.winrate.domain.models.HeroRoles
import com.example.winrate.domain.viewmodels.OneHeroViewModel

class OneHeroViewModelFactory(
    private val heroProperty: HeroProperty,
    private val heroRoles: MutableList<HeroRoles>,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OneHeroViewModel::class.java)) {
            return OneHeroViewModel(heroProperty,heroRoles, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}