package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.enitity.CharacterRM

class DeleteDataUseCase(private val repository: Repository) {
    suspend fun deleteData(characterRM: CharacterRM) {
        repository.deleteData(characterRM)
    }
}