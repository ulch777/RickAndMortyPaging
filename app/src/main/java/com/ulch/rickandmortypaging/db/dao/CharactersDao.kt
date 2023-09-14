package com.ulch.rickandmortypaging.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulch.rickandmortypaging.db.entity.CharacterEntity

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<CharacterEntity>)

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun remoteId(id: Int): CharacterEntity?

    @Query("SELECT * FROM characters")
    fun observeAll(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}