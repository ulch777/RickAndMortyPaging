package com.ulch.rickandmortypaging.ui.character.load_state_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ulch.rickandmortypaging.R
import com.ulch.rickandmortypaging.databinding.CharacterLoadStateFooterViewItemBinding

class DataLoadStateViewHolder(
    private val binding: CharacterLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): DataLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.character_load_state_footer_view_item, parent, false)
            val binding = CharacterLoadStateFooterViewItemBinding.bind(view)
            return DataLoadStateViewHolder(binding, retry)
        }
    }
}