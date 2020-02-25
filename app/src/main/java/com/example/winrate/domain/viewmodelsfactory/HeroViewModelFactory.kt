package com.example.winrate.domain.viewmodelsfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.winrate.data.local.dao.HeroInformationDao
import com.example.winrate.domain.viewmodels.AllHeroesViewModel

class HeroViewModelFactory(
    private val database: HeroInformationDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllHeroesViewModel::class.java)) {
            return AllHeroesViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}