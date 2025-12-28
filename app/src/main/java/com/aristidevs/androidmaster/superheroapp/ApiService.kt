package com.aristidevs.androidmaster.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/2564de6719646ebfe19abb67c46ff2e9/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

    @GET("/api/2564de6719646ebfe19abb67c46ff2e9/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId: String): Response<SuperheroDetailResponse>
}
