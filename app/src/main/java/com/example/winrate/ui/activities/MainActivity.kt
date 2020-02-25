package com.example.winrate.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.winrate.R
import com.example.winrate.databinding.ActivityMainBinding
import com.example.winrate.domain.viewmodels.MainActivityViewModel
import com.example.winrate.domain.viewmodelsfactory.MainActivityViewModelFactory
import com.example.winrate.ui.adapters.GeneralViewPagerAdapter
import com.example.winrate.ui.fragments.FirstIntroFragment
import com.example.winrate.ui.fragments.SecondIntroFragment
import com.example.winrate.ui.fragments.ThirdIntroFragment


class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var navigation : NavController
    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            //binding = ActivityMainBinding.inflate(layoutInflater)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setToolbar()
        setUpBottomNavigationBar()
        setUpViewModel()
    }


    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory()).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.toolbarProperty.observe(this, Observer {
            it?.let {
                supportActionBar?.title = it
            }
        })
    }

    private fun setToolbar() {
        setSupportActionBar(binding.mainActivityToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_search_hero_menu, menu)
        val search = menu?.findItem(R.id.searchHero)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun setUpBottomNavigationBar() {

        navigation = Navigation.findNavController(this,R.id.myNavHostFragment)

        binding.bottomNavigationView.setupWithNavController(navigation)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.GameLearn -> {
                    navigation.navigate(R.id.welcomeDotaFragment)
                    true
                }
                R.id.HeroList -> {
                    navigation.navigate(R.id.allHeroesFragment)
                    true
                }
                R.id.GamesAnallis -> {
                    navigation.navigate(R.id.analisisGameFragment)
                    true
                }
                R.id.DotaNews -> {
                    navigation.navigate(R.id.dotaNewsFragment)
                    true
                }
                R.id.TournamentsList -> {
                    navigation.navigate(R.id.tournamentsFragment)
                    true
                }
                else -> false
            }
        }
    }
}
