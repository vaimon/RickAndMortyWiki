package me.vaimon.rickandmortywiki.ui.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.domain.usecase.GetCharactersPageUseCase
import me.vaimon.rickandmortywiki.data.mapper.Mapper
import me.vaimon.rickandmortywiki.models.CharacterBaseInfo
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersPageUseCase: GetCharactersPageUseCase,
    private val characterBaseInfoMapper: Mapper<CharacterBaseInfo, CharacterEntity>
): ViewModel() {
    private val _characterList: MutableLiveData<List<CharacterBaseInfo>> = MutableLiveData()
    val characterList: LiveData<List<CharacterBaseInfo>> = _characterList

    fun fetchCharacters(){
        viewModelScope.launch {
            _characterList.value = getCharactersPageUseCase.invoke().map{
                characterBaseInfoMapper.from(it)
            }
        }
    }
}