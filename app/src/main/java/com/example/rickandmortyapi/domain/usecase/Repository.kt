package com.example.rickandmortyapi.domain.usecase

import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.response.CharacterResponse
import retrofit2.Response

interface Repository {
    suspend fun getCharacter(id: Int): Response<CharacterResponse>
    suspend fun readData(): List<CharacterRM>
    suspend fun readDataById(id: Int): CharacterRM
    suspend fun insertData(characterRM: CharacterRM)
    suspend fun deleteData(characterRM: CharacterRM)
}