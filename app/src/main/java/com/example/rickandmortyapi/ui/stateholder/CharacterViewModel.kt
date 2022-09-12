package com.example.rickandmortyapi.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.local.room.CharacterDatabase
import com.example.rickandmortyapi.data.remote.repository.Repository
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel : ViewModel() {
    var repo = Repository()

    private val _characterResponse = MutableLiveData<Response<CharacterResponse>>(null)
    val characterResponse: LiveData<Response<CharacterResponse>> = _characterResponse

    private val _characterRM = MutableLiveData<List<CharacterRM>>(null)
    val characterRM: LiveData<List<CharacterRM>> = _characterRM

    private val _characterRMById = MutableLiveData<CharacterRM>(null)
    val characterRMById: LiveData<CharacterRM> = _characterRMById

    private val _isDeleted = MutableLiveData<Boolean>(null)
    val isDeleted: LiveData<Boolean> = _isDeleted

    fun loadCharacterData(id: Int) {
        viewModelScope.launch {
            _characterResponse.value = repo.getCharacter(id)
        }
    }

    fun getCharacterFromDB(characterDb: CharacterDatabase) {
        viewModelScope.launch {
            _characterRM.value = repo.readData(characterDb)
        }
    }

    fun getCharacterFromDbById(characterDb: CharacterDatabase, id: Int) {
        viewModelScope.launch {
            _characterRMById.value = repo.readDataById(characterDb, id)
        }
    }

    fun writeCharacterToDB(characterDb: CharacterDatabase, characterRM: CharacterRM) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertData(characterDb, characterRM)
        }
    }

    fun deleteCharacterFromDB(characterDB: CharacterDatabase, characterRM: CharacterRM) {
        viewModelScope.launch() {
            repo.deleteData(characterDB, characterRM)
            _isDeleted.value = true
        }
    }

    fun resetDeletedValue() {
        viewModelScope.launch {
            _isDeleted.value = false
        }
    }
}