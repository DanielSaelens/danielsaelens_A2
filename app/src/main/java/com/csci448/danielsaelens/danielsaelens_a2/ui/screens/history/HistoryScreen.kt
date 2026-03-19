package com.csci448.danielsaelens.danielsaelens_a2.ui.screens.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.csci448.danielsaelens.danielsaelens_a2.data.GameRecord

@Composable
fun HistoryScreen(
    gameRecords: List<GameRecord>,
    playerOneWins: Int,
    playerOneLosses: Int,
    playerOneTies: Int,
    playerTwoWins: Int,
    playerTwoLosses: Int,
    playerTwoTies: Int,
    computerWins: Int,
    computerLosses: Int,
    computerTies: Int,
    onNavigateBack: () -> Unit
) {

}



