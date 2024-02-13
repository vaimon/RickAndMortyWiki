package me.vaimon.rickandmortywiki.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _uiState: MutableLiveData<UiState> = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun emulatePredefinedSettingsFetching(){
        viewModelScope.launch {
            delay(2000L)
            _uiState.value = _uiState.value!!.copy(isReady = true)
        }
    }

    data class UiState(val isReady: Boolean = false)
}