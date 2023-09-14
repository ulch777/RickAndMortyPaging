package com.ulch.rickandmortypaging.ui.character.adapter.character

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ulch.rickandmortypaging.R
import com.ulch.rickandmortypaging.databinding.ItemCharacterBinding
import com.ulch.rickandmortypaging.model.UiModel

class CharacterViewHolder(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: UiModel.CharacterModel?) {
        if (data == null) {
            val resources = itemView.resources
            binding.tvName.text = resources.getString(R.string.loading)
            binding.tvGender.text = resources.getString(R.string.loading)
            binding.tvOrigin.text = resources.getString(R.string.loading)
            binding.tvLocation.text = resources.getString(R.string.loading)
            binding.tvType.text = resources.getString(R.string.loading)
        } else {
            showData(data)
        }
    }

    private fun showData(characterModel: UiModel.CharacterModel) {
        binding.image.clipToOutline = true
        Glide.with(binding.image.context)
            .load(characterModel.image)
            .placeholder(R.drawable.alien_avatar_svgrepo_com)
            .fitCenter()
            .into(binding.image)
        binding.tvName.text = characterModel.name
        binding.tvStatus.text = characterModel.status
        binding.tvGender.text = characterModel.gender
        binding.tvLocation.text = characterModel.location
        binding.tvOrigin.text = characterModel.origin
        binding.tvType.text = characterModel.type
        binding.tvSpecies.text = characterModel.species
        val statusColor = when (characterModel.status) {
            Status.STATUS_ALIVE.status -> Color.GREEN
            Status.STATUS_DEAD.status -> Color.RED
            else -> Color.YELLOW
        }
        binding.tvStatus.setTextColor(statusColor)
    }

}

enum class Status(val status: String) {
    STATUS_ALIVE("Alive"),
    STATUS_DEAD("Dead")
}