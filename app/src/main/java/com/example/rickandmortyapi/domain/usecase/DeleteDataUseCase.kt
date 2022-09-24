package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.usecase.DataBaseRepository

class DeleteDataUseCase(private val dataBaseRepository: DataBaseRepository) {
    suspend fun deleteData(characterDatabase: CharacterDatabase, characterRM: CharacterRM) {
        dataBaseRepository.deleteData(characterDatabase, characterRM)
    }
}