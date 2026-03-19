package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state

data class SettingsState(
    val numPlayers: Int = 1,
    val difficulty: String = "Easy",
    val whoGoesFirst: String = "Player One",
    val theme: String = "Monsters Inc"
)
