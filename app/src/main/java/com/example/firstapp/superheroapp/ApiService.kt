package com.example.firstapp.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET ("/api/153e859fa2b240b1960f675764d88967/search/{name}")
    suspend fun getSuperHero(@Path("name")superHeroName : String) : Response<SuperHeroDataResponse>

    @GET ("/api/153e859fa2b240b1960f675764d88967/search/{character-id}")

    suspend fun getSuperHeroDetails(@Path("character-id") superHeroId:String):Response<SuperHeroDetailResponse>
}