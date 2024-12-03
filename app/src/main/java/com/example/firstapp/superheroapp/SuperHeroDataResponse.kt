package com.example.firstapp.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(@SerializedName("response") val response: String,
    @SerializedName("results") val superHeros : List<SuperHeroItemResponse>)


data class SuperHeroItemResponse (
    @SerializedName("id") val Superheroid: String ,
    @SerializedName("name") val SuperheroNAme: String,
    @SerializedName("image")val superHeroImgResponse: SuperHeroImgResponse
    )

data class SuperHeroImgResponse(
    @SerializedName("url") val url:String
)


