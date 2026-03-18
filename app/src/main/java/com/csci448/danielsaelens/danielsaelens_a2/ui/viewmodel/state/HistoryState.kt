package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state

import com.csci448.danielsaelens.danielsaelens_a2.data.GameRecord

data class HistoryState(
    val gameRecords: List<GameRecord> = listOf(),
    val playerOneWins: Int = 0,
    val playerOneLosses: Int = 0,
    val playerOneTies: Int = 0,
    val playerTwoWins: Int = 0,
    val playerTwoLosses: Int = 0,
    val playerTwoTies: Int = 0,
    val computerWins: Int = 0,
    val computerLosses: Int = 0,
    val computerTies: Int = 0,


)
