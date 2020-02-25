package com.example.winrate.domain.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.winrate.data.local.dao.HeroInformationDao
import com.example.winrate.data.local.models.Hero
import com.example.winrate.data.remote.openDotaApi.HeroesApi
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import com.example.winrate.domain.convertors.HeroApiStatus
import kotlinx.coroutines.*


class AllHeroesViewModel(
    private val database: HeroInformationDao,
    application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //Room heroes database
    val heroes = database.getAllHeroes()

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<HeroProperty>>()
    val properties: LiveData<List<HeroProperty>>
        get() = _properties

    private val _navigateToSelectedHero = MutableLiveData<HeroProperty>()
    val navigateToSelectedHero: LiveData<HeroProperty>
        get() = _navigateToSelectedHero




    init {
        Log.i("AllHeroesViewModel", "Created!")
        getAllHeroes()
    }

    private fun getAllHeroes() {
        coroutineScope.launch {
            val getPropertiesDiffered = HeroesApi.retrofitService.getHeroesAsync()
            try {
                _status.value = HeroApiStatus.LOADING.toString() + " - almost done!"
                val listResult = getPropertiesDiffered.await()
                if(listResult.isNotEmpty()){
                    _properties.value = listResult
                    _status.value = HeroApiStatus.DONE.toString() + " - now you can see all heroes!"
                }
            } catch (t: Throwable){
                _status.value = HeroApiStatus.ERROR.toString() + " - ohh, something went wrong..."
                _properties.value = ArrayList()
            }
        }
    }

    fun refreshHeroes(){
        getAllHeroes()
    }

    private fun initHero() {
        uiScope.launch {
            //hero.value = getFirstHeroFromDatabase()
        }
    }

    fun insertHero() {
        uiScope.launch {
            val newHero = Hero()
            insert(newHero)
        }
    }

    private suspend fun insert(newHero: Hero) {
        withContext(Dispatchers.IO){
            database.insert(newHero)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("AllHeroesViewModel", "Destroyed!")
    }

    fun displayPossibleHeroDetails(heroProperty: HeroProperty){
        _navigateToSelectedHero.value = heroProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedHero.value = null
    }
}