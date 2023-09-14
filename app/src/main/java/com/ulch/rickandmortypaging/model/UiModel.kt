package com.ulch.rickandmortypaging.model

sealed class UiModel {
    data class CharacterModel(
        val id: Int,
        val name: String,
        val status: String,
        val type: String,
        val gender: String,
        val image: String,
        val species: String,
        val location: String,
        val origin: String,
    ): UiModel()

    data class EpisodeModel(
        val id: Int,
        val name: String,
        val airDate: String,
        val episode: String,
    ): UiModel()

    data class LocationModel(
        val id: Int,
        val dimension: String,
        val name: String,
        val type: String,
    ): UiModel()
}