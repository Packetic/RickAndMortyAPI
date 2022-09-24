package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.usecase.DataBaseRepository

class InsertDataUseCase(private val dataBaseRepository: DataBaseRepository) {
    suspend fun insertData(characterDatabase: CharacterDatabase, characterRM: CharacterRM) {
        dataBaseRepository.insertData(characterDatabase, characterRM)
    }
}