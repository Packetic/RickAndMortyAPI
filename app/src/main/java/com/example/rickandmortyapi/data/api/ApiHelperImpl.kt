package com.example.rickandmortyapi.data.api

import com.example.rickandmortyapi.data.api.ApiService
import com.example.rickandmortyapi.domain.response.CharacterResponse
import com.example.rickandmortyapi.domain.usecase.ApiHelper
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelperImpl @Inject constructor(val apiService: ApiService) : ApiHelper {
    override suspend fun getCharacter(id: Int): Response<CharacterResponse> {
        return apiService.getCharacter(id)
    }
}