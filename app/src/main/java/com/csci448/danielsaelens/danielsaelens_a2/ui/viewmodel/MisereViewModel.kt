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




    fun handleIntent(intent: MisereIntent){
        when(intent){

        }
    }


}