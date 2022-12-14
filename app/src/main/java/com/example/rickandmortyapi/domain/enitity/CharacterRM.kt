package com.example.rickandmortyapi.domain.enitity

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class CharacterRM(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "origin") val origin: String?,
    @ColumnInfo(name = "image") val image: Bitmap?,
)