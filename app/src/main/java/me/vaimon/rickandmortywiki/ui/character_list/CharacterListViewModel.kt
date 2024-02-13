package me.vaimon.rickandmortywiki.ui.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.vaimon.rickandmortywiki.ui.character_list.models.CharacterBaseInfo

class CharacterListViewModel: ViewModel() {
    private val _characterList: MutableLiveData<List<CharacterBaseInfo>> = MutableLiveData()
    val characterList: LiveData<List<CharacterBaseInfo>> = _characterList

    fun fetchCharacters(){
        _characterList.value = listOf(CharacterBaseInfo(1, "Rick"), CharacterBaseInfo(2, "Morty"))
    }
}