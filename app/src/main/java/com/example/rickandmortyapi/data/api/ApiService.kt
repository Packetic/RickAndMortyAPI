package com.example.rickandmortyapi.data.api

import com.example.rickandmortyapi.domain.response.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterResponse>
}