package com.example.rickandmortyapi.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyapi.domain.dao.CharacterDAO
import com.example.rickandmortyapi.domain.enitity.CharacterRM

@Database(entities = [CharacterRM::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
}