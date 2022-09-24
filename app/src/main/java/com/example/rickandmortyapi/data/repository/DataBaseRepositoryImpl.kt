package com.example.rickandmortyapi.data.repository

import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.data.api.RetrofitInstance
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.response.CharacterResponse
import com.example.rickandmortyapi.domain.usecase.DataBaseRepository
import retrofit2.Response

object DataBaseRepositoryImpl: DataBaseRepository {
    override suspend fun readData(characterDb: CharacterDatabase): List<CharacterRM> {
        return characterDb.characterDao().getAll()
    }
    override suspend fun readDataById(characterDb: CharacterDatabase, id: Int): CharacterRM {
        return characterDb.characterDao().getById(id)
    }
    override suspend fun insertData(characterDb: CharacterDatabase, characterRM: CharacterRM) {
        characterDb.characterDao().insert(characterRM)
    }
    override suspend fun deleteData(characterDb: CharacterDatabase, characterRM: CharacterRM) {
        characterDb.characterDao().delete(characterRM)
    }
}