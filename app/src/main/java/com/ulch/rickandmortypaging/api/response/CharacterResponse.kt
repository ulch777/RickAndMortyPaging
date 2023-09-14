package com.ulch.rickandmortypaging.api.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @field:SerializedName("results")
    val results: List<CharacterResultsItem>? = null,

    @field:SerializedName("info")
    val info: Info? = null
)

data class Location(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)

data class Origin(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)

data class CharacterResultsItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("species")
    val species: String? = null,

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("origin")
    val origin: Origin? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: Location? = null,

    @field:SerializedName("episode")
    val episode: List<String?>? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
