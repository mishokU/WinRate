package com.example.winrate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.winrate.databinding.CellRoleBinding
import com.example.winrate.domain.models.HeroRoles
import com.example.winrate.ui.util.HeroRolesListener


class HeroRolesAdapter(private val onClickListener: HeroRolesListener): ListAdapter<HeroRoles, HeroRolesAdapter.HeroRoleViewHolder>(DiffCallback) {


    companion object DiffCallback: DiffUtil.ItemCallback<HeroRoles>() {
        override fun areItemsTheSame(oldItem: HeroRoles, newItem: HeroRoles): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HeroRoles, newItem: HeroRoles): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : HeroRoleViewHolder {
        return HeroRoleViewHolder(CellRoleBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HeroRoleViewHolder, position: Int){
        val heroProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(heroProperty)
        }
        holder.bind(heroProperty)
    }

    class HeroRoleViewHolder(private val binding: CellRoleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(heroProperty: HeroRoles){
            binding.name = heroProperty
            binding.executePendingBindings()
        }
    }
}