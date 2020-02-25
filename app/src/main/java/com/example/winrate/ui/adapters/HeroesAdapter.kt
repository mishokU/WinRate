package com.example.winrate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.winrate.data.remote.openDotaApi.models.HeroProperty
import com.example.winrate.databinding.CellHeroBinding
import com.example.winrate.ui.util.HeroesAdapterListener

class HeroesAdapter(private val onClickListener: HeroesAdapterListener): ListAdapter<HeroProperty, HeroesAdapter.HeroViewHolder>(DiffCallback) {


    companion object DiffCallback: DiffUtil.ItemCallback<HeroProperty>() {
        override fun areItemsTheSame(oldItem: HeroProperty, newItem: HeroProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HeroProperty, newItem: HeroProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : HeroViewHolder {
        return HeroViewHolder(CellHeroBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int){
        val heroProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(heroProperty)
        }
        holder.bind(heroProperty)
    }

    class HeroViewHolder(private val binding: CellHeroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(heroProperty: HeroProperty){
            binding.property = heroProperty
            binding.executePendingBindings()
        }
    }
}

