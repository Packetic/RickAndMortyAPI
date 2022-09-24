package com.example.rickandmortyapi.ui

import com.example.rickandmortyapi.domain.room.CharacterDatabase

// TODO: there is a dependency from data layer
// TODO: don't fix until DI is injected
interface ProvideDataBase {
    fun provideDataBase(): CharacterDatabase
}