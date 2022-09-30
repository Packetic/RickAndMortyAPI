package com.example.rickandmortyapi.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.example.rickandmortyapi.data.local.DataBaseRepositoryImpl
import com.example.rickandmortyapi.data.repository.Repository
import com.example.rickandmortyapi.domain.enitity.CharacterRM
import com.example.rickandmortyapi.domain.response.CharacterResponse
import com.example.rickandmortyapi.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository) : ViewModel() {
    //    var retrofitRepository = RetrofitRepositoryImpl()
    var getCharacterUseCase = GetCharacterUseCase(repository)
    var dataBaseRepository = DataBaseRepositoryImpl()
    var readDataUseCase = ReadDataUseCase(dataBaseRepository)
    var readDataByIdUseCase = ReadDataByIdUseCase(dataBaseRepository)
    var insertDataUseCase = InsertDataUseCase(dataBaseRepository)
    var deleteDataUseCase = DeleteDataUseCase(dataBaseRepository)

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
            _characterResponse.value = getCharacterUseCase.getCharacter(id)
        }
    }

    fun getCharacterFromDB(characterDb: CharacterDatabase) {
        viewModelScope.launch {
            _characterRM.value = readDataUseCase.readData(characterDb)
        }
    }

    fun getCharacterFromDbById(characterDb: CharacterDatabase, id: Int) {
        viewModelScope.launch {
            _characterRMById.value = readDataByIdUseCase.readDataById(characterDb, id)
        }
    }

    fun writeCharacterToDB(characterDb: CharacterDatabase, characterRM: CharacterRM) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.insertData(characterDb, characterRM)
        }
    }

    fun deleteCharacterFromDB(characterDB: CharacterDatabase, characterRM: CharacterRM) {
        viewModelScope.launch {
            deleteDataUseCase.deleteData(characterDB, characterRM)
            _isDeleted.value = true
            _characterRM.value = readDataUseCase.readData(characterDB)
        }
    }

    fun resetDeletedValue() {
        viewModelScope.launch {
            _isDeleted.value = false
        }
    }
}