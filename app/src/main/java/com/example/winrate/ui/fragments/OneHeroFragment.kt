package com.example.winrate.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.winrate.databinding.FragmentOneHeroBinding
import com.example.winrate.domain.models.HeroRoles
import com.example.winrate.domain.viewmodels.OneHeroViewModel
import com.example.winrate.domain.viewmodelsfactory.OneHeroViewModelFactory
import com.example.winrate.ui.adapters.HeroFullRolesAdapter
import com.example.winrate.ui.adapters.HeroRolesAdapter
import com.example.winrate.ui.util.HeroRolesListener

class OneHeroFragment : Fragment() {

    private lateinit var viewModel: OneHeroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application
        val binding = FragmentOneHeroBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val heroProperty = OneHeroFragmentArgs.fromBundle(arguments!!).selectedProperty

        val heroRoles: MutableList<HeroRoles> = mutableListOf()
        for(role in heroProperty.roles){
            heroRoles.add(HeroRoles(0,role))
        }

        val viewModelFactory = OneHeroViewModelFactory(heroProperty,heroRoles, application)


        viewModel = ViewModelProvider(this, viewModelFactory).get(OneHeroViewModel::class.java)

        viewModel.getHeroBio(heroProperty.id)

        binding.rolesRecyclerView.adapter = HeroRolesAdapter(HeroRolesListener {
            viewModel.displayRoleDescription(it)
        })

        binding.fullRolesRecyclerView.adapter = HeroFullRolesAdapter()

        viewModel.showProgressBio.observe(viewLifecycleOwner, Observer {
            if(it) {
                binding.progressOneHeroBioLoading.visibility = View.VISIBLE
            } else {
                binding.progressOneHeroBioLoading.visibility = View.INVISIBLE
            }
        })

        viewModel.expandHeroBio.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.oneHeroBioTv.maxLines = 100
            } else{
                binding.oneHeroBioTv.maxLines = 3
            }
        })

        viewModel.showFullRole.observe(viewLifecycleOwner, Observer {
            if(it){
                binding.fullRolesRecyclerView.visibility = View.GONE
                binding.collapseFullRoles.visibility = View.GONE
            } else{
                binding.fullRolesRecyclerView.visibility = View.VISIBLE
                binding.collapseFullRoles.visibility = View.VISIBLE
            }
        })


        binding.viewModel = viewModel


        return binding.root
    }
}
