package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.data.repository.RetrofitRepositoryImpl
import com.example.rickandmortyapi.domain.response.CharacterResponse
import retrofit2.Response

class GetCharacterUseCase(private val repository: RetrofitRepository) {
    suspend fun getCharacter(id: Int): Response<CharacterResponse> {
        return repository.getCharacter(id)
    }
}