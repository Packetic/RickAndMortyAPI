package com.example.rickandmortyapi.domain.dao

import androidx.room.*
import com.example.rickandmortyapi.domain.enitity.Character

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characters_table")
    fun getAll(): List<Character>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Character)

    @Delete
    suspend fun delete(character: Character)
}