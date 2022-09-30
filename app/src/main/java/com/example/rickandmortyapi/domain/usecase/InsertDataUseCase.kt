package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.enitity.CharacterRM

class InsertDataUseCase(private val repository: Repository) {
    suspend fun insertData(characterRM: CharacterRM) {
        repository.insertData(characterRM)
    }
}