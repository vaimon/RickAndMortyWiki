package me.vaimon.rickandmortywiki.ui.character_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.vaimon.rickandmortywiki.domain.entities.CharacterEntity
import me.vaimon.rickandmortywiki.domain.usecase.GetCharactersPageUseCase
import me.vaimon.rickandmortywiki.data.mapper.Mapper
import me.vaimon.rickandmortywiki.models.SeriesCharacter
import me.vaimon.rickandmortywiki.utils.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersPageUseCase: GetCharactersPageUseCase,
    private val seriesCharacterMapper: Mapper<SeriesCharacter, CharacterEntity>
): ViewModel() {
    private val _characterList: MutableLiveData<List<SeriesCharacter>> = MutableLiveData()
    val characterList: LiveData<List<SeriesCharacter>> = _characterList

    private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    val navigateToDetailsEvent: SingleLiveEvent<SeriesCharacter> = SingleLiveEvent()

    init {
        requestNextPage()
    }

    private fun requestNextPage(lastCharacterId: Int? = null){
        viewModelScope.launch(Dispatchers.Main) {
            _uiState.value = _uiState.value?.copy(isDataLoading = true)
            try{
                getCharactersPageUseCase.invoke(lastCharacterId).map{
                    seriesCharacterMapper.from(it)
                }.also{
                    _characterList.value = _characterList.value?.plus(it) ?: it
                }
            } catch (e: Exception){
                _uiState.value = _uiState.value?.copy(errorMessage = e.localizedMessage)
            }
            _uiState.value = _uiState.value?.copy(isDataLoading = false)
        }
    }

    fun onListBottomReached(lastCharacterId: Int?) {
        if(_uiState.value?.isDataLoading == true)
            return
        requestNextPage(lastCharacterId)
    }

    fun onErrorMessageShown(){
        _uiState.value = _uiState.value?.copy(errorMessage = null)
    }

    fun onCharacterClick(character: SeriesCharacter) {
        navigateToDetailsEvent.value = character
    }

    data class UiState(
        val isDataLoading: Boolean = false,
        val errorMessage: String? = null,
    )
}