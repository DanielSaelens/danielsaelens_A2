package com.csci448.danielsaelens.danielsaelens_a2.ui.navigation.specs
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.danielsaelens.danielsaelens_a2.ui.screens.history.HistoryScreen
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel

object HistoryScreenSpec : IScreenSpec {

    override val route = "history"
    @Composable
    override fun Content(
        modifier: Modifier,
        misereViewModel: MisereViewModel,
        navController: NavController
    ) {
        val historyState = misereViewModel.historyState.value

        HistoryScreen(
            gameRecords = historyState.gameRecords,
            playerOneWins = historyState.playerOneWins,
            playerOneLosses = historyState.playerOneLosses,
            playerOneTies = historyState.playerOneTies,
            playerTwoWins = historyState.playerTwoWins,
            playerTwoLosses = historyState.playerTwoLosses,
            playerTwoTies = historyState.playerTwoTies,
            computerWins = historyState.computerWins,
            computerLosses = historyState.computerLosses,
            computerTies = historyState.computerTies,
            onNavigateBack = { navController.popBackStack() }
        )


    }
}