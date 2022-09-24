package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.usecase.DataBaseRepository

class ReadDataUseCase(private val dataBaseRepository: DataBaseRepository) {
    suspend fun readData(characterDatabase: CharacterDatabase): List<CharacterRM> {
        return dataBaseRepository.readData(characterDatabase)
    }
}