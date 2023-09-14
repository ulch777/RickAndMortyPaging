package com.ulch.rickandmortypaging.ui.character.adapter.episode

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ulch.rickandmortypaging.databinding.ItemEpisodeBinding
import com.ulch.rickandmortypaging.model.UiModel

class EpisodeAdapter :
    PagingDataAdapter<UiModel.EpisodeModel, EpisodeViewHolder>(EPISODE_COMPARATOR) {

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val uiModel = getItem(position)
        Log.e("onBindViewHolder", uiModel.toString())
        uiModel.let {
            holder.bind(uiModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val EPISODE_COMPARATOR =
            object : DiffUtil.ItemCallback<UiModel.EpisodeModel>() {
                override fun areItemsTheSame(
                    oldItem: UiModel.EpisodeModel,
                    newItem: UiModel.EpisodeModel
                ): Boolean {
                    return oldItem.id == newItem.id
                            && oldItem.name == newItem.name
                            && oldItem.airDate == newItem.airDate
                            && oldItem.episode == newItem.episode
                }

                override fun areContentsTheSame(
                    oldItem: UiModel.EpisodeModel,
                    newItem: UiModel.EpisodeModel
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }
}