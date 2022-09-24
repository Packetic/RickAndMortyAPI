package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.usecase.DataBaseRepository

class ReadDataByIdUseCase(private val dataBaseRepository: DataBaseRepository) {
    suspend fun readDataById(characterDatabase: CharacterDatabase, id: Int): CharacterRM {
        return dataBaseRepository.readDataById(characterDatabase, id)
    }
}