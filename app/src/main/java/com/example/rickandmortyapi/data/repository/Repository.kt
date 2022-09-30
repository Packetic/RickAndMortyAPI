package com.example.rickandmortyapi.data.repository

import com.example.rickandmortyapi.domain.usecase.ApiHelper
import javax.inject.Inject

class Repository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getCharacter(id: Int) = apiHelper.getCharacter(id)
}