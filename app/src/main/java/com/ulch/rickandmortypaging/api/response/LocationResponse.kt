package com.ulch.rickandmortypaging.api.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(

    @field:SerializedName("results")
    val results: List<LocationResultsItem>? = null,

    @field:SerializedName("info")
    val info: Info? = null
)

data class LocationResultsItem(

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("residents")
    val residents: List<String?>? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("dimension")
    val dimension: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)


