package com.example.rickandmortyapi.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyapi.data.api.ApiService
import com.example.rickandmortyapi.data.api.ApiHelperImpl
import com.example.rickandmortyapi.data.local.DatabaseHelperImpl
import com.example.rickandmortyapi.data.repository.RepositoryImpl
import com.example.rickandmortyapi.domain.dao.CharacterDAO
import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.domain.usecase.ApiHelper
import com.example.rickandmortyapi.domain.usecase.DatabaseHelper
import com.example.rickandmortyapi.domain.usecase.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesBaseUrl(): String = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    fun provideCharacterDao(characterDatabase: CharacterDatabase): CharacterDAO =
        characterDatabase.characterDao()

    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext appContext: Context): CharacterDatabase =
        Room.databaseBuilder(
            appContext,
            CharacterDatabase::class.java,
            "character_database"
        ).build()

    @Provides
    @Singleton
    fun provideDatabaseHelper(databaseHelper: DatabaseHelperImpl): DatabaseHelper = databaseHelper

    @Provides
    @Singleton
    fun provideRepository(repository: RepositoryImpl): Repository = repository
}