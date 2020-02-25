package com.example.winrate.domain.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.winrate.R
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import com.example.winrate.data.remote.openDotaStratz.HeroStratzApi
import com.example.winrate.data.remote.openDotaStratz.models.HeroStratz
import com.example.winrate.domain.models.HeroRoles
import com.example.winrate.domain.models.SingleRole
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneHeroViewModel(heroProperty: HeroProperty,heroRoles: MutableList<HeroRoles>, app: Application): AndroidViewModel(app) {


    private val _selectedProperty = MutableLiveData<HeroProperty>()
    val selectedProperty: LiveData<HeroProperty>
        get() = _selectedProperty

    private val _heroRoles = MutableLiveData<List<HeroRoles>>()
    val heroRoles: LiveData<List<HeroRoles>>
        get() = _heroRoles

    private val _heroBio = MutableLiveData<String>()
    val heroBio: LiveData<String>
        get() = _heroBio

    private val _expandHeroBio = MutableLiveData<Boolean>()
    val expandHeroBio: LiveData<Boolean>
        get() = _expandHeroBio

    private val _showProgressBio = MutableLiveData<Boolean>()
    val showProgressBio: LiveData<Boolean>
        get() = _showProgressBio

    private val _showFullRole = MutableLiveData<Boolean>()
    val showFullRole: LiveData<Boolean>
        get() = _showFullRole

    private val _fullRoleList = MutableLiveData<MutableList<SingleRole>>()
    val fullRoleList: LiveData<MutableList<SingleRole>>
        get() = _fullRoleList

    private val _showHelperSnackBar = MutableLiveData<Boolean>()
    val showHelpersSnackBar: LiveData<Boolean>
        get() = showHelpersSnackBar


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        _selectedProperty.value = heroProperty
        _heroRoles.value = heroRoles
        createFullRole(heroRoles, app)
    }

    private fun createFullRole(heroRoles: MutableList<HeroRoles>, app: Application){
        val fullRoles: MutableList<SingleRole> = mutableListOf()
        for(role in heroRoles){
            when(role.roles){
                "Disabler" -> fullRoles.add(SingleRole(role.id, role.roles, app.resources.getString(R.string.disabler)))
                "Escape" -> fullRoles.add(SingleRole(role.id, role.roles, app.resources.getString(R.string.disabler)))
                "Jungler" -> fullRoles.add(SingleRole(role.id,role.roles, app.resources.getString(R.string.disabler)))
                "Durable" -> fullRoles.add(SingleRole(role.id,role.roles, app.resources.getString(R.string.disabler)))
                "Nuker" -> fullRoles.add(SingleRole(role.id,role.roles, app.resources.getString(R.string.disabler)))
                "Pusher" -> fullRoles.add(SingleRole(role.id,role.roles, app.resources.getString(R.string.disabler)))
                "Initiator" -> fullRoles.add(SingleRole(role.id,role.roles, app.resources.getString(R.string.disabler)))
                "Carry" -> fullRoles.add(SingleRole(role.id,role.roles, app.resources.getString(R.string.carry)))
                "Support" -> fullRoles.add(SingleRole(role.id, role.roles,app.resources.getString(R.string.support)))
            }
        }
        _fullRoleList.value = fullRoles
    }

    fun getHeroBio(id: Int){
        coroutineScope.launch {
            HeroStratzApi.retrofitService.getHeroesBio().enqueue(object :
                Callback<MutableMap<String, HeroStratz>> {


                override fun onFailure(call: Call<MutableMap<String, HeroStratz>>, t: Throwable) {
                    _heroBio.value = "There is no bio ("
                }

                override fun onResponse(
                    call: Call<MutableMap<String, HeroStratz>>,
                    response: Response<MutableMap<String, HeroStratz>>) {
                    _showProgressBio.value = false
                    _heroBio.value = getBioCurrentHero(response.body()!!, id)
                }
            })
        }
    }

    private fun getBioCurrentHero(heroesBio: MutableMap<String,HeroStratz>,id: Int): String?{
        return heroesBio[id.toString()]!!.language.bio
    }

    fun displayRoleDescription(it: HeroRoles) {
        //Snackbar.make()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("AllHeroesViewModel", "Destroyed!")
    }

    fun stateProgress() : Int {
        return if(_showProgressBio.value == true){
            _showProgressBio.value = false
            View.INVISIBLE
        } else {
            _showProgressBio.value = true
            View.VISIBLE
        }
    }

    fun expandFullRole(){
        _showFullRole.value = _showFullRole.value != true
    }

    fun expandHeroBio(){
        _expandHeroBio.value = _expandHeroBio.value != true
    }

    companion object {
        const val attackRange: String = "Attack range"
        const val turnRate: String = "Hero turn rate"
        const val inCm: String = "In captains mode"
    }


}