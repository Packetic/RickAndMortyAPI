package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.enitity.CharacterRM

class ReadDataByIdUseCase(private val repository: Repository) {
    suspend fun readDataById(id: Int): CharacterRM {
        return repository.readDataById(id)
    }
}