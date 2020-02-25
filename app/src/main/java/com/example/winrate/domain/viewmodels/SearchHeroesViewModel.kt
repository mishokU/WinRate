package com.example.winrate.domain.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty

class SearchHeroesViewModel(heroProperty: HeroProperty, app: Application): AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<HeroProperty>()
    val selectedProperty: LiveData<HeroProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = heroProperty
    }
}