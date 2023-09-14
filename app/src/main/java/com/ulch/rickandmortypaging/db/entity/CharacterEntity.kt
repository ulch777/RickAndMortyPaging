package com.ulch.rickandmortypaging.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val type: String,
    val gender: String,
    val image: String,
    val species: String,
    val location: String,
    val origin: String,
    val next: Int? = null,
    val prev: Int? = null,
)
