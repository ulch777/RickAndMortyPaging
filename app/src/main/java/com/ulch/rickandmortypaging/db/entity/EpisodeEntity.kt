package com.ulch.rickandmortypaging.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val next: Int? = null,
    val prev: Int? = null,
)