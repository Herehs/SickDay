package com.example.up.presentation.screens.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up.domain.model.UserSettings
import com.example.up.domain.use_case.GetSettingsUseCase
import com.example.up.domain.use_case.UpdateSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase,
) : ViewModel() {
    private val _settingsState = MutableStateFlow(UserSettings())
    val settingsState = _settingsState.asStateFlow()

    fun updateSettings(newSettings:(UserSettings) -> UserSettings){
        viewModelScope.launch {
            updateSettingsUseCase(newSettings)
        }
    }

    init {
        viewModelScope.launch {
            getSettingsUseCase().collect {
                _settingsState.value = it
            }
        }
    }
}