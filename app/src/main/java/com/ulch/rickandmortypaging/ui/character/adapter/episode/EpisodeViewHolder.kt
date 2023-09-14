package com.ulch.rickandmortypaging.ui.character.adapter.episode

import androidx.recyclerview.widget.RecyclerView
import com.ulch.rickandmortypaging.R
import com.ulch.rickandmortypaging.databinding.ItemEpisodeBinding
import com.ulch.rickandmortypaging.model.UiModel

class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: UiModel.EpisodeModel?) {
        if (data == null) {
            val resources = itemView.resources
            binding.tvEpisode.text = resources.getString(R.string.loading)
            binding.tvDate.text = resources.getString(R.string.loading)
            binding.tvName.text = resources.getString(R.string.loading)
        } else {
            showData(data)
        }
    }

    private fun showData(data: UiModel.EpisodeModel) {
        binding.tvEpisode.text = data.episode
        binding.tvDate.text = data.airDate
        binding.tvName.text = data.name
    }

}
