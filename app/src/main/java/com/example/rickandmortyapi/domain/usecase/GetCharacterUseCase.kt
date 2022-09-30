package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.response.CharacterResponse
import retrofit2.Response

class GetCharacterUseCase(private val repository: Repository) {
    suspend fun getCharacter(id: Int): Response<CharacterResponse> {
        return repository.getCharacter(id)
    }
}