package com.example.rickandmortyapi.data.remote.repository

import com.example.rickandmortyapi.data.local.room.CharacterDatabase
import com.example.rickandmortyapi.data.remote.api.RetrofitInstance
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.response.CharacterResponse
import retrofit2.Response

class Repository {
    suspend fun getCharacter(id: Int): Response<CharacterResponse> {
        return RetrofitInstance.api.getCharacter(id)
    }
    suspend fun readData(characterDb: CharacterDatabase): List<CharacterRM> {
        return characterDb.characterDao().getAll()
    }
    suspend fun insertData(characterDb: CharacterDatabase, characterRM: CharacterRM) {
        characterDb.characterDao().insert(characterRM)
    }
    suspend fun deleteData(characterDb: CharacterDatabase, characterRM: CharacterRM) {
        characterDb.characterDao().delete(characterRM)
    }
}