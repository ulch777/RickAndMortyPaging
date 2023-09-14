package com.ulch.rickandmortypaging.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulch.rickandmortypaging.db.entity.LocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<LocationEntity>)

    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun remoteId(id: Int): LocationEntity?

    @Query("SELECT * FROM locations")
    fun observeAll(): PagingSource<Int, LocationEntity>

    @Query("DELETE FROM locations")
    suspend fun clearAll()
}