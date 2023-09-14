package com.ulch.rickandmortypaging.ui.main_menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulch.rickandmortypaging.databinding.ItemMenuBinding

class MenuAdapter(private val list: List<String>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(holder.bindingAdapterPosition)
        }
        holder.bind(list[position])

    }

    class MenuViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.itemText.text = item
        }
    }
}