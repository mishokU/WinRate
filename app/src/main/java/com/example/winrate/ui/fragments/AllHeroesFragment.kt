package com.example.winrate.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.winrate.R
import com.example.winrate.data.local.database.HeroesDatabase
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import com.example.winrate.databinding.FragmentAllHeroesBinding
import com.example.winrate.domain.viewmodels.AllHeroesViewModel
import com.example.winrate.domain.viewmodels.MainActivityViewModel
import com.example.winrate.domain.viewmodelsfactory.HeroViewModelFactory
import com.example.winrate.domain.viewmodelsfactory.MainActivityViewModelFactory
import com.example.winrate.ui.adapters.HeroesAdapter
import com.example.winrate.ui.util.HeroesAdapterListener
import kotlinx.coroutines.*

class AllHeroesFragment : Fragment() {

    private lateinit var binding: FragmentAllHeroesBinding
    private lateinit var allHeroesViewModel: AllHeroesViewModel
    private lateinit var fragmentJob: Job
    private lateinit var uiScope: CoroutineScope


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllHeroesBinding.inflate(inflater)

        createCoroutine()
        createViewModel()
        initFab()

        return binding.root
    }

    private fun initFab() {
        binding.searchHeroFab.setOnClickListener {
            val bundle = bundleOf("heroes" to allHeroesViewModel.properties.value)
            this.findNavController().navigate(R.id.searchHeroesFragment, bundle)
        }
    }

    private fun createCoroutine() {
        fragmentJob = Job()
        uiScope = CoroutineScope(Dispatchers.Main + fragmentJob)
    }

    private fun createViewModel() {

        val application = requireNotNull(this.activity).application
        val dataSource = HeroesDatabase.getInstance(application).heroInformationDao
        val heroViewModelFactory = HeroViewModelFactory(dataSource, application)

        allHeroesViewModel = ViewModelProvider(this, heroViewModelFactory).get(AllHeroesViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = allHeroesViewModel

        binding.allHeroesRv.adapter = HeroesAdapter(HeroesAdapterListener {
            allHeroesViewModel.displayPossibleHeroDetails(it)
        })

        allHeroesViewModel.navigateToSelectedHero.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(AllHeroesFragmentDirections.actionAllHeroesFragmentToOneHeroFragment(it))
                allHeroesViewModel.displayPropertyDetailsComplete()
            }
        })

        allHeroesViewModel.status.observe(viewLifecycleOwner, Observer {
            it?.let{
                binding.responce.text = it
                uiScope.launch {
                    hideStatusBar(it)
                }
            }
        })
    }

    private suspend fun hideStatusBar(it: String) {
        withContext(Dispatchers.Main){
            when {
                it.contains("DONE") -> {
                    binding.refreshAllHeroesData.visibility = View.INVISIBLE
                    binding.responce.visibility = View.INVISIBLE
                    binding.searchHeroFab.visibility = View.VISIBLE
                }
                it.contains("ERROR") -> {
                    binding.responce.setTextColor(resources.getColor(R.color.colorLightRed))
                    binding.refreshAllHeroesData.visibility = View.VISIBLE
                    binding.responce.visibility = View.VISIBLE
                }
                it.contains("LOADING") -> {
                    binding.responce.setTextColor(resources.getColor(R.color.colorWhite))
                    binding.responce.visibility = View.VISIBLE
                }
            }
        }
    }

}
