package com.example.rickandmortyapi.data.remote.repository

import com.example.rickandmortyapi.data.remote.api.RetrofitInstance
import com.example.rickandmortyapi.model.Character
import retrofit2.Response

class Repository {
    suspend fun getCharacter(id: Int): Response<Character> {
        return RetrofitInstance.api.getCharacter(id)
    }
}