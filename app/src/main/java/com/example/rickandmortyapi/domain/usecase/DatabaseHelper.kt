package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.domain.enitity.CharacterRM

interface DatabaseHelper {
    suspend fun readData(): List<CharacterRM>
    suspend fun readDataById(id: Int): CharacterRM
    suspend fun insertData(characterRM: CharacterRM)
    suspend fun deleteData(characterRM: CharacterRM)
}