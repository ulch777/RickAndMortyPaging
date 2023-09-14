package com.ulch.rickandmortypaging.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ulch.rickandmortypaging.api.RickAndMortyService
import com.ulch.rickandmortypaging.data.remote_mediator.CharacterRemoteMediator
import com.ulch.rickandmortypaging.data.remote_mediator.EpisodeRemoteMediator
import com.ulch.rickandmortypaging.data.remote_mediator.LocationRemoteMediator
import com.ulch.rickandmortypaging.db.RickAndMortyDatabase
import com.ulch.rickandmortypaging.db.entity.CharacterEntity
import com.ulch.rickandmortypaging.db.entity.EpisodeEntity
import com.ulch.rickandmortypaging.db.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RickAndMortyRepository(
    private val service: RickAndMortyService,
    private val database: RickAndMortyDatabase
) {
    fun getCharacterStream(): Flow<PagingData<CharacterEntity>> {

        val pagingSourceFactory = { database.charactersDao().observeAll() }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = CharacterRemoteMediator(service, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun getEpisodeStream(): Flow<PagingData<EpisodeEntity>> {

        val pagingSourceFactory = { database.episodesDao().observeAll() }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = EpisodeRemoteMediator(service, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun getLocationStream(): Flow<PagingData<LocationEntity>> {

        val pagingSourceFactory = { database.locationsDao().observeAll() }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = LocationRemoteMediator(service, database),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
