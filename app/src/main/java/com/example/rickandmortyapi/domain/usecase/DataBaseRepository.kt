package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.response.CharacterResponse
import retrofit2.Response

interface DataBaseRepository {
    suspend fun readData(characterDb: CharacterDatabase): List<CharacterRM>
    suspend fun readDataById(characterDb: CharacterDatabase, id: Int): CharacterRM
    suspend fun insertData(characterDb: CharacterDatabase, characterRM: CharacterRM)
    suspend fun deleteData(characterDb: CharacterDatabase, characterRM: CharacterRM)
}