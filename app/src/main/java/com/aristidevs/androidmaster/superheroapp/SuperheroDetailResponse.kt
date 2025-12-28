package com.aristidevs.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

// cada uno de las propiedades de SuperHeroDetailResponse es como un arbol de la respuesta de la api
// name no pero image, powerstats, y bioagraphy dentro tienen mas de 1 atributo por lo que se crea un "objeto"
// de tipo PowerStatsResponse o SuperheroImageDetailResponse o lo que sea que dentro lleva las lineas que nos
// interesan de la respuesta de la api
data class SuperheroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperheroImageDetailResponse,
    @SerializedName("biography") val biography: Biography
)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class SuperheroImageDetailResponse(
    @SerializedName("url") val url: String
)

data class Biography(
    @SerializedName("full-name") val fullname: String,
    @SerializedName("publisher") val publisher: String
)