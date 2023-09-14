package com.ulch.rickandmortypaging.ui.character.adapter.location

import androidx.recyclerview.widget.RecyclerView
import com.ulch.rickandmortypaging.R
import com.ulch.rickandmortypaging.databinding.ItemLocationBinding
import com.ulch.rickandmortypaging.model.UiModel

class LocationViewHolder(private val binding: ItemLocationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: UiModel.LocationModel?) {
        if (data == null) {
            val resources = itemView.resources
            binding.tvName.text = resources.getString(R.string.loading)
            binding.tvType.text = resources.getString(R.string.loading)
        } else {
            showData(data)
        }
    }

    private fun showData(data: UiModel.LocationModel) {
        binding.tvName.text = data.name
        binding.tvType.text = data.type
    }

}
