package com.example.rickandmortyapi.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.data.remote.repository.Repository
import com.example.rickandmortyapi.model.Character
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel : ViewModel() {
    var repo = Repository()

    private val _character = MutableLiveData<Response<Character>>(null)
    val character: LiveData<Response<Character>> = _character

    fun loadCharacterData(id: Int) {
        viewModelScope.launch {
            _character.value = repo.getCharacter(id)
        }
    }
}