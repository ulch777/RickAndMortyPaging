package com.ulch.rickandmortypaging.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulch.rickandmortypaging.db.entity.EpisodeEntity

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<EpisodeEntity>)

    @Query("SELECT * FROM episodes WHERE id = :id")
    suspend fun remoteId(id: Int): EpisodeEntity?

    @Query("SELECT * FROM episodes")
    fun observeAll(): PagingSource<Int, EpisodeEntity>

    @Query("DELETE FROM episodes")
    suspend fun clearAll()
}