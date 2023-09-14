package com.ulch.rickandmortypaging.api.response

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(

    @field:SerializedName("results")
    val results: List<EpisodeResultsItem>? = null,

    @field:SerializedName("info")
    val info: Info? = null
)

data class EpisodeResultsItem(

    @field:SerializedName("air_date")
    val airDate: String? = null,

    @field:SerializedName("characters")
    val characters: List<String?>? = null,

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("episode")
    val episode: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("url")
    val url: String? = null
)


