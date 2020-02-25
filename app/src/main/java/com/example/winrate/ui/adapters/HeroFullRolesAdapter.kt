package com.example.winrate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.winrate.databinding.CellFullRoleBinding
import com.example.winrate.domain.models.SingleRole

class HeroFullRolesAdapter : ListAdapter<SingleRole, HeroFullRolesAdapter.HeroFullRoleViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<SingleRole>() {
        override fun areItemsTheSame(oldItem: SingleRole, newItem: SingleRole): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SingleRole, newItem: SingleRole): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : HeroFullRoleViewHolder {
        return HeroFullRoleViewHolder(CellFullRoleBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HeroFullRoleViewHolder, position: Int){
        val heroProperty = getItem(position)
        holder.bind(heroProperty)
    }

    class HeroFullRoleViewHolder(private val binding: CellFullRoleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(heroProperty: SingleRole){
            binding.fullRoleProperty = heroProperty
            binding.executePendingBindings()
        }
    }
}