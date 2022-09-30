package com.example.rickandmortyapi.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    var getCharacterUseCase = GetCharacterUseCase(repository)
    var readDataUseCase = ReadDataUseCase(repository)
    var readDataByIdUseCase = ReadDataByIdUseCase(repository)
    var insertDataUseCase = InsertDataUseCase(repository)
    var deleteDataUseCase = DeleteDataUseCase(repository)

    private val _characterResponse = MutableLiveData<Response<CharacterResponse>>(null)
    val characterResponse: LiveData<Response<CharacterResponse>> = _characterResponse

    private val _characterRM = MutableLiveData<List<CharacterRM>>(null)
    val characterRM: LiveData<List<CharacterRM>> = _characterRM

    private val _characterRMById = MutableLiveData<CharacterRM>(null)
    val characterRMById: LiveData<CharacterRM> = _characterRMById

    fun loadCharacterData(id: Int) {
        viewModelScope.launch {
            _characterResponse.value = getCharacterUseCase.getCharacter(id)
        }
    }

    fun getCharacterFromDB() {
        viewModelScope.launch {
            _characterRM.value = readDataUseCase.readData()
        }
    }

    fun getCharacterFromDbById(id: Int) {
        viewModelScope.launch {
            _characterRMById.value = readDataByIdUseCase.readDataById(id)
        }
    }

    fun writeCharacterToDB(characterRM: CharacterRM) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDataUseCase.insertData(characterRM)
        }
    }

    fun deleteCharacterFromDB(characterRM: CharacterRM) {
        viewModelScope.launch {
            deleteDataUseCase.deleteData(characterRM)
            _characterRM.value = readDataUseCase.readData()
        }
    }
}