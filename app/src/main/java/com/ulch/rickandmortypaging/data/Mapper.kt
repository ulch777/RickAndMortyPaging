package com.ulch.rickandmortypaging.data

import com.ulch.rickandmortypaging.api.response.CharacterResultsItem
import com.ulch.rickandmortypaging.api.response.EpisodeResultsItem
import com.ulch.rickandmortypaging.api.response.LocationResultsItem
import com.ulch.rickandmortypaging.db.entity.CharacterEntity
import com.ulch.rickandmortypaging.db.entity.EpisodeEntity
import com.ulch.rickandmortypaging.db.entity.LocationEntity
import com.ulch.rickandmortypaging.model.UiModel

fun CharacterResultsItem.toCharacter(): CharacterEntity {
    return CharacterEntity(
        id = this.id,
        name = if (this.name.isNullOrEmpty()) "Unknown" else this.name,
        status = if (this.status.isNullOrEmpty()) "Unknown" else this.status,
        type = if (this.type.isNullOrEmpty()) "Unknown" else this.type,
        gender = if (this.gender.isNullOrEmpty()) "Unknown" else this.gender,
        image = if (this.image.isNullOrEmpty()) "Unknown" else this.image,
        species = if (this.species.isNullOrEmpty()) "Unknown" else this.species,
        location = this.location?.name ?: "Unknown",
        origin = this.origin?.name ?: "Unknown",
    )
}

fun List<CharacterResultsItem>.toCharacterList(): List<CharacterEntity> {
    return this.map { resultItem ->
        resultItem.toCharacter()
    }
}

fun CharacterEntity.toCharacterModel(): UiModel.CharacterModel {
    return UiModel.CharacterModel(
        id = this.id,
        name = this.name,
        status = this.status,
        type = this.type,
        gender = this.gender,
        image = this.image,
        species = this.species,
        location = this.location,
        origin = this.origin,
    )
}

fun EpisodeResultsItem.toEpisode(): EpisodeEntity {
    return EpisodeEntity(
        id = this.id,
        name = if (this.name.isNullOrEmpty()) "Unknown" else this.name,
        airDate = if (this.airDate.isNullOrEmpty()) "Unknown" else this.airDate,
        episode = if (this.episode.isNullOrEmpty()) "Unknown" else this.episode,
    )
}

fun List<EpisodeResultsItem>.toEpisodeList(): List<EpisodeEntity> {
    return this.map { resultItem ->
        resultItem.toEpisode()
    }
}

fun EpisodeEntity.toEpisodeModel(): UiModel.EpisodeModel {
    return UiModel.EpisodeModel(
        id = this.id,
        name = this.name,
        airDate = this.airDate,
        episode = this.episode
    )
}

fun LocationResultsItem.toLocation(): LocationEntity {
    return LocationEntity(
        id = this.id,
        name = if (this.name.isNullOrEmpty()) "Unknown" else this.name,
        dimension = if (this.dimension.isNullOrEmpty()) "Unknown" else this.dimension,
        type = if (this.type.isNullOrEmpty()) "Unknown" else this.type
    )
}

fun List<LocationResultsItem>.toLocationList(): List<LocationEntity> {
    return this.map { resultItem ->
        resultItem.toLocation()
    }
}

fun LocationEntity.toLocationModel(): UiModel.LocationModel {
    return UiModel.LocationModel(
        id = this.id,
        name = this.name,
        dimension = this.dimension,
        type = this.type,
    )
}
