package com.example.winrate.domain.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.winrate.domain.viewmodels.MainActivityViewModel

class MainActivityViewModelFactory: ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}