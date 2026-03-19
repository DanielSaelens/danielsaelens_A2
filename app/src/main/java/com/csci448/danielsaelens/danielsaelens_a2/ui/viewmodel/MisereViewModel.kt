package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract.GameContract
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract.HistoryContract
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract.SettingsContract
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract.WelcomeContract
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.effect.MisereEffect
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.intent.MisereIntent
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.GameState
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.HistoryState
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.SettingsState
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.WelcomeState


class MisereViewModel() : ViewModel() {

    private val _welcomeState = mutableStateOf(WelcomeState())
    private val _settingsState = mutableStateOf(SettingsState())
    private val _historyState = mutableStateOf(HistoryState())
    private val _gameState = mutableStateOf(GameState())
    private val _effectState = mutableStateOf<MisereEffect?>(null)

    val welcomeState = derivedStateOf { _welcomeState.value }
    val settingsState = derivedStateOf { _settingsState.value }
    val historyState = derivedStateOf { _historyState.value }
    val gameState = derivedStateOf { _gameState.value }

    val welcomeContract = WelcomeContract(
        state = _welcomeState,
        effect = _effectState,
        viewModel = this@MisereViewModel
    )
    val settingsContract = SettingsContract(
        state = _settingsState,
        effect = _effectState,
        viewModel = this@MisereViewModel
    )
    val historyContract = HistoryContract(
        state = _historyState,
        effect = _effectState,
        viewModel = this@MisereViewModel
    )

    val gameContract = GameContract(
        state = _gameState,
        effect = _effectState,
        viewModel = this@MisereViewModel
    )




    fun handleIntent(intent: MisereIntent) {
        when(intent) {
            is MisereIntent.WelcomeIntent -> handleWelcomeIntent(intent)
            is MisereIntent.SettingsIntent -> handleSettingsIntent(intent)
            is MisereIntent.HistoryIntent -> handleHistoryIntent(intent)
            is MisereIntent.GameIntent -> handleGameIntent(intent)
        }
    }

    private fun handleWelcomeIntent(intent: MisereIntent.WelcomeIntent) {
        when(intent) {
            is MisereIntent.WelcomeIntent.NavigateToGame -> { }
            is MisereIntent.WelcomeIntent.NavigateToHistory -> { }
            is MisereIntent.WelcomeIntent.NavigateToSettings -> { }
            is MisereIntent.WelcomeIntent.Quit -> { }
        }
    }

    private fun handleSettingsIntent(intent: MisereIntent.SettingsIntent) {
        when(intent) {
            is MisereIntent.SettingsIntent.ClearHistory -> {
                _historyState.value = HistoryState()
            }

            is MisereIntent.SettingsIntent.ChangeNumPlayers -> {
                _settingsState.value = _settingsState.value.copy(numPlayers = intent.numPlayers)
            }

            is MisereIntent.SettingsIntent.ChangeCompDifficulty -> {
                _settingsState.value = _settingsState.value.copy(difficulty = intent.compDifficulty)
            }

            is MisereIntent.SettingsIntent.ChangeGoesFirst -> {
                _settingsState.value =_settingsState.value.copy(whoGoesFirst = intent.whoFirst)
            }
            is MisereIntent.SettingsIntent.ChangeTheme -> {
                _settingsState.value =_settingsState.value.copy(theme = intent.theme)
            }
        }
    }

    private fun handleHistoryIntent(intent: MisereIntent.HistoryIntent) {
        when(intent) {
            is MisereIntent.HistoryIntent.NavigateBack -> { }
        }
    }

    private fun handleGameIntent(intent: MisereIntent.GameIntent) {
        when(intent) {
            is MisereIntent.GameIntent.CellClicked -> { }
            is MisereIntent.GameIntent.PlayAgain -> { }
            is MisereIntent.GameIntent.NavigateBackToHistory -> { }
            is MisereIntent.GameIntent.NavigateBack -> { }
        }
    }


}