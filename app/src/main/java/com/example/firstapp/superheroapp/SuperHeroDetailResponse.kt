package com.example.firstapp.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: SuperHeroStatsResponse,
    @SerializedName("image") val image: SuperHeroImageDetail,
    @SerializedName("biography") val biography: SuperHeroBiography


)

data class SuperHeroStatsResponse(
    @SerializedName("intelligence") val intelligence: String = "0",
    @SerializedName("strength") val strength: String = "0",
    @SerializedName("speed") val speed: String = "0",
    @SerializedName("durability") val durability: String = "0",
    @SerializedName("power") val power: String = "0",
    @SerializedName("combat") val combat:String = "0"

)

data class SuperHeroImageDetail(@SerializedName("url") val url: String)

data class SuperHeroBiography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)