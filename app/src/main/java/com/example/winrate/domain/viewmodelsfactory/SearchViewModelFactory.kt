package com.example.winrate.domain.viewmodelsfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import com.example.winrate.domain.viewmodels.SearchHeroesViewModel

class SearchViewModelFactory(
    private val heroes: List<HeroProperty>,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchHeroesViewModel::class.java)) {
            return SearchHeroesViewModel(heroes, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}