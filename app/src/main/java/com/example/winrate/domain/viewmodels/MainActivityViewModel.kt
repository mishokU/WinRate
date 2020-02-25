package com.example.winrate.domain.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private val _toolbarProperty = MutableLiveData<String>()
    val toolbarProperty: LiveData<String>
        get() = _toolbarProperty

    fun setTitle(title: String){
        _toolbarProperty.value = title
    }
}