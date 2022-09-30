package com.example.rickandmortyapi.data.repository

import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.usecase.ApiHelper
import com.example.rickandmortyapi.domain.usecase.DatabaseHelper
import com.example.rickandmortyapi.domain.usecase.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val databaseHelper: DatabaseHelper
    ): Repository {
    override suspend fun getCharacter(id: Int) = apiHelper.getCharacter(id)
    override suspend fun readData() = databaseHelper.readData()
    override suspend fun readDataById(id: Int) = databaseHelper.readDataById(id)
    override suspend fun insertData(characterRM: CharacterRM) = databaseHelper.insertData(characterRM)
    override suspend fun deleteData(characterRM: CharacterRM) = databaseHelper.deleteData(characterRM)
}