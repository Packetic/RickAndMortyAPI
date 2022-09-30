package com.example.rickandmortyapi.data.local

import com.example.rickandmortyapi.domain.dao.CharacterDAO
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.usecase.DatabaseHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHelperImpl @Inject constructor(private val characterDAO: CharacterDAO) : DatabaseHelper {
    override suspend fun readData(): List<CharacterRM> = characterDAO.getAll()
    override suspend fun readDataById(id: Int): CharacterRM = characterDAO.getById(id)
    override suspend fun insertData(characterRM: CharacterRM) = characterDAO.insert(characterRM)
    override suspend fun deleteData(characterRM: CharacterRM) = characterDAO.delete(characterRM)
}