package com.ulch.rickandmortypaging.ui.character

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.ulch.rickandmortypaging.data.RickAndMortyRepository
import com.ulch.rickandmortypaging.data.toCharacterModel
import com.ulch.rickandmortypaging.data.toEpisodeModel
import com.ulch.rickandmortypaging.data.toLocationModel
import com.ulch.rickandmortypaging.model.UiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataListViewModel(
    private val repository: RickAndMortyRepository
) : ViewModel() {
    lateinit var pagingDataFlow: Flow<PagingData<UiModel>>
    fun initDataFlow(type: Int) {
        pagingDataFlow = when (type) {
            0 -> getCharacters()
            1 -> getLocations()
            2 -> getEpisodes()
            else -> {
                throw Exception("Unknown type")
            }
        }
    }

    private fun getCharacters(): Flow<PagingData<UiModel>> {
        return repository.getCharacterStream()
            .map { pagingData ->
                pagingData.map {
                    it.toCharacterModel()
                }
            }
    }

    private fun getLocations(): Flow<PagingData<UiModel>> {
        return repository.getLocationStream()
            .map { pagingData ->
                pagingData.map {
                    it.toLocationModel()
                }
            }
    }

    private fun getEpisodes(): Flow<PagingData<UiModel>> {
        return repository.getEpisodeStream()
            .map { pagingData ->
                pagingData.map {
                    it.toEpisodeModel()
                }
            }
    }
}