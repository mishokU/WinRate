package com.example.winrate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import com.example.winrate.databinding.FragmentSearchHeroesBinding
import com.example.winrate.domain.viewmodels.SearchHeroesViewModel
import com.example.winrate.domain.viewmodelsfactory.SearchViewModelFactory
import com.example.winrate.ui.adapters.HeroesAdapter
import com.example.winrate.ui.util.HeroesAdapterListener
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SearchHeroesFragment : Fragment() {

    private lateinit var binding: FragmentSearchHeroesBinding
    private lateinit var viewModel: SearchHeroesViewModel
    private lateinit var heroList: List<HeroProperty>

    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchHeroesBinding.inflate(inflater)

        getHeroes()
        initViewModel()
        initRv()
        createFilter()

        return binding.root
    }

    private fun initRv() {
        binding.searchAllHeroesRv.adapter = HeroesAdapter(HeroesAdapterListener {
            viewModel.displayPossibleHeroDetails(it)
        })
    }

    private fun initViewModel() {
        val application = requireNotNull(this.activity).application
        val searchViewModelFactory = SearchViewModelFactory(heroList, application)

        viewModel = ViewModelProvider(this,searchViewModelFactory).get(SearchHeroesViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToSelectedHero.observe(viewLifecycleOwner, Observer {
            if(null != it){
                //this.findNavController().navigate(AllHeroesFragmentDirections.actionAllHeroesFragmentToOneHeroFragment(it))
                //viewModel.displayPropertyDetailsComplete()
            }
        })
    }

    private fun getHeroes() {
        if(arguments != null){
           heroList = arguments!!["heroes"] as List<HeroProperty>
        }
    }

    private fun createFilter() {
        compositeDisposable.add(
            RxTextView.textChanges(binding.searchHeroText)
                .skipInitialValue()
                .debounce(700, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .doOnNext {
                    viewModel.setSearchHeroQuery(it)
                }
                .subscribe()
        )
    }

}
