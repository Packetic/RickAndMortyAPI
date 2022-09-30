package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.enitity.CharacterRM

class ReadDataUseCase(private val repository: Repository) {
    suspend fun readData(): List<CharacterRM> {
        return repository.readData()
    }
}