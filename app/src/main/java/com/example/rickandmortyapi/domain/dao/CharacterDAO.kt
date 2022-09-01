package com.example.rickandmortyapi.domain.dao

import androidx.room.*
import com.example.rickandmortyapi.domain.enitity.CharacterRM

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characters_table")
    suspend fun getAll(): List<CharacterRM>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterRM: CharacterRM)

    @Delete
    suspend fun delete(characterRM: CharacterRM)
}