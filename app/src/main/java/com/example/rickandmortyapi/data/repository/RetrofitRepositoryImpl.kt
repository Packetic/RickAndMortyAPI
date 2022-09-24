package com.example.rickandmortyapi.data.repository

import com.example.rickandmortyapi.data.api.RetrofitInstance
import com.example.rickandmortyapi.domain.response.CharacterResponse
import com.example.rickandmortyapi.domain.usecase.RetrofitRepository
import retrofit2.Response

object RetrofitRepositoryImpl: RetrofitRepository {
    override suspend fun getCharacter(id: Int): Response<CharacterResponse> {
        return RetrofitInstance.api.getCharacter(id)
    }
}