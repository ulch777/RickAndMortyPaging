package com.ulch.rickandmortypaging.ui.character.adapter.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ulch.rickandmortypaging.databinding.ItemCharacterBinding
import com.ulch.rickandmortypaging.model.UiModel

class CharacterAdapter :
    PagingDataAdapter<UiModel.CharacterModel, CharacterViewHolder>(CHARACTER_COMPARATOR) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            holder.bind(uiModel)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val CHARACTER_COMPARATOR =
            object : DiffUtil.ItemCallback<UiModel.CharacterModel>() {
                override fun areItemsTheSame(
                    oldItem: UiModel.CharacterModel,
                    newItem: UiModel.CharacterModel
                ): Boolean {
                    return oldItem.id == newItem.id
                            && oldItem.gender == newItem.gender
                            && oldItem.name == newItem.name
                            && oldItem.image == newItem.image
                            && oldItem.species == newItem.species
                            && oldItem.status == newItem.status
                            && oldItem.type == newItem.type
                }

                override fun areContentsTheSame(
                    oldItem: UiModel.CharacterModel,
                    newItem: UiModel.CharacterModel
                ): Boolean {
                    return oldItem == newItem
                }

            }

    }
}