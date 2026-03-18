package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state

data class GameState(
    val board: List<List<String>> = List(4) { List(4) { "" } },
    val currentTurn: String = "Player One",
    val gameOver: Boolean = false,
    val winner: String? = null



)
