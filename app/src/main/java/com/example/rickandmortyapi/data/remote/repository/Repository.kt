package com.example.rickandmortyapi.data.remote.repository

import com.example.rickandmortyapi.data.remote.api.RetrofitInstance
import com.example.rickandmortyapi.domain.response.CharacterResponse
import retrofit2.Response

class Repository {
    suspend fun getCharacter(id: Int): Response<CharacterResponse> {
        return RetrofitInstance.api.getCharacter(id)
    }
}