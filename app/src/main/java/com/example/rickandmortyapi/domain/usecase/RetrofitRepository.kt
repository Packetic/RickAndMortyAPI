package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.response.CharacterResponse
import retrofit2.Response

interface RetrofitRepository {
    suspend fun getCharacter(id: Int): Response<CharacterResponse>
}