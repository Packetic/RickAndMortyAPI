package com.example.rickandmortyapi.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.remote.repository.Repository
import com.example.rickandmortyapi.domain.response.CharacterResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel : ViewModel() {
    var repo = Repository()

    private val _characterResponse = MutableLiveData<Response<CharacterResponse>>(null)
    val characterResponse: LiveData<Response<CharacterResponse>> = _characterResponse

    fun loadCharacterData(id: Int) {
        viewModelScope.launch {
            _characterResponse.value = repo.getCharacter(id)
        }
    }
}