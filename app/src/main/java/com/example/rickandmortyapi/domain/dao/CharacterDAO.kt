package com.example.rickandmortyapi.domain.dao

import androidx.room.*
import com.example.rickandmortyapi.domain.enitity.CharacterRM

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characters_table")
    suspend fun getAll(): List<CharacterRM>

    @Query("SELECT * FROM characters_table WHERE id = :id")
    suspend fun getById(id: Int): CharacterRM

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterRM: CharacterRM)

    @Delete
    suspend fun delete(characterRM: CharacterRM)
}