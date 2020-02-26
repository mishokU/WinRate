package com.example.winrate.domain.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import kotlinx.coroutines.*

class SearchHeroesViewModel(heroes: List<HeroProperty>, app: Application): AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<List<HeroProperty>>()
    val selectedProperty: LiveData<List<HeroProperty>>
        get() = _selectedProperty

    private val _filteredList = MutableLiveData<List<HeroProperty>>()
    val filteredList: LiveData<List<HeroProperty>>
        get() = _filteredList

    private val _searchText = MutableLiveData<String>()
    val searchText: LiveData<String>
        get() = _searchText

    private val _navigateToSelectedHero = MutableLiveData<HeroProperty>()
    val navigateToSelectedHero: LiveData<HeroProperty>
        get() = _navigateToSelectedHero


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        _selectedProperty.value = heroes
    }

    fun setSearchHeroQuery(query : String){
        coroutineScope.launch {
            _filteredList.value = getFilteredList(query)
        }
    }

    private fun getFilteredList(query: String): List<HeroProperty>? {
        val list: MutableList<HeroProperty> = mutableListOf()
        for(element in _selectedProperty.value!!){
            if(element.localized_name.contains(query)) {
                list.add(element)
            }
        }
        return list
    }

    fun displayPossibleHeroDetails(heroProperty: HeroProperty){
        _navigateToSelectedHero.value = heroProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedHero.value = null
    }
}
