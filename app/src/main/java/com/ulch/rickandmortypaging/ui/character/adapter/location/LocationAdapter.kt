package com.ulch.rickandmortypaging.ui.character.adapter.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ulch.rickandmortypaging.databinding.ItemLocationBinding
import com.ulch.rickandmortypaging.model.UiModel

class LocationAdapter :
    PagingDataAdapter<UiModel.LocationModel, LocationViewHolder>(LOCATION_COMPARATOR) {

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            holder.bind(uiModel)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val LOCATION_COMPARATOR =
            object : DiffUtil.ItemCallback<UiModel.LocationModel>() {
                override fun areItemsTheSame(
                    oldItem: UiModel.LocationModel,
                    newItem: UiModel.LocationModel
                ): Boolean {
                    return oldItem.id == newItem.id
                            && oldItem.dimension == newItem.dimension
                            && oldItem.name == newItem.name
                            && oldItem.type == newItem.type
                }

                override fun areContentsTheSame(
                    oldItem: UiModel.LocationModel,
                    newItem: UiModel.LocationModel
                ): Boolean {
                    return oldItem == newItem
                }

            }

    }
}