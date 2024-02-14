package me.vaimon.rickandmortywiki.ui.character_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.vaimon.rickandmortywiki.models.SeriesCharacter

class CharacterInfoViewModel: ViewModel() {
    private val _characterInfo: MutableLiveData<SeriesCharacter> = MutableLiveData()
    val characterInfo: LiveData<SeriesCharacter> = _characterInfo

    fun setCharacterInfo(characterInfo: SeriesCharacter) {
        _characterInfo.value = characterInfo
    }
}