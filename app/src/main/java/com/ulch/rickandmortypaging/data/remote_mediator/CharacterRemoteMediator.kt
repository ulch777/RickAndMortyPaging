package com.ulch.rickandmortypaging.data.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ulch.rickandmortypaging.api.RickAndMortyService
import com.ulch.rickandmortypaging.data.toCharacterList
import com.ulch.rickandmortypaging.db.RickAndMortyDatabase
import com.ulch.rickandmortypaging.db.entity.CharacterEntity
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val service: RickAndMortyService,
    private val database: RickAndMortyDatabase
) : RemoteMediator<Int, CharacterEntity>() {

    private val STARTING_PAGE_INDEX = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val data = getCharacterClosestToCurrentPosition(state)
                data?.next?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val data = getCharacterForFirstItem(state)
                val prevKey = data?.prev
                    ?: return MediatorResult.Success(endOfPaginationReached = data != null)
                prevKey
            }

            LoadType.APPEND -> {
                val data = getCharacterForLastItem(state)
                val nextKey = data?.next
                    ?: return MediatorResult.Success(endOfPaginationReached = data != null)
                nextKey
            }
        }

        try {
            val apiResponse = service.getCharacters(page)
            val results = apiResponse.results
            val info = apiResponse.info
            val endOfPaginationReached = results?.isEmpty() ?: false
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.charactersDao().clearAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey =
                    if (endOfPaginationReached || info?.pages == page + 1) null else page + 1

                val charactersDb = results?.toCharacterList()
                val dbList = charactersDb?.map {
                    it.copy(
                        next = nextKey,
                        prev = prevKey,
                    )
                }
                dbList?.let {
                    database.charactersDao().insertAll(it)
                }

            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getCharacterForLastItem(state: PagingState<Int, CharacterEntity>): CharacterEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                database.charactersDao().remoteId(character.id)
            }
    }

    private suspend fun getCharacterForFirstItem(state: PagingState<Int, CharacterEntity>): CharacterEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                database.charactersDao().remoteId(character.id)
            }
    }

    private suspend fun getCharacterClosestToCurrentPosition(state: PagingState<Int, CharacterEntity>): CharacterEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                database.charactersDao().remoteId(repoId)
            }
        }
    }

}