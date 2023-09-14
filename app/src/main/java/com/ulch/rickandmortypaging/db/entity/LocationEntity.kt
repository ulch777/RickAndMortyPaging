package com.ulch.rickandmortypaging.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey val id: Int,
    val dimension: String,
    val name: String,
    val type: String,
    val next: Int? = null,
    val prev: Int? = null,
)
