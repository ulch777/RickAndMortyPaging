package com.ulch.rickandmortypaging.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ulch.rickandmortypaging.db.dao.CharactersDao
import com.ulch.rickandmortypaging.db.dao.EpisodeDao
import com.ulch.rickandmortypaging.db.dao.LocationDao
import com.ulch.rickandmortypaging.db.entity.CharacterEntity
import com.ulch.rickandmortypaging.db.entity.EpisodeEntity
import com.ulch.rickandmortypaging.db.entity.LocationEntity

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun episodesDao(): EpisodeDao
    abstract fun locationsDao(): LocationDao

    companion object {

        @Volatile
        private var INSTANCE: RickAndMortyDatabase? = null

        fun getInstance(context: Context): RickAndMortyDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RickAndMortyDatabase::class.java, "RickAndMortyDatabase.db"
            )
                .build()
    }
}