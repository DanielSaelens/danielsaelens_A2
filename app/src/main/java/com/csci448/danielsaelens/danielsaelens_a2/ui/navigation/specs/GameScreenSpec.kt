package com.csci448.danielsaelens.danielsaelens_a2.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.danielsaelens.danielsaelens_a2.ui.screens.game.GameScreen
import com.csci448.danielsaelens.danielsaelens_a2.ui.screens.history.HistoryScreen
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.intent.MisereIntent

object GameScreenSpec : IScreenSpec{
    override val route = "game"
    @Composable
    override fun Content(
        modifier: Modifier,
        misereViewModel: MisereViewModel,
        navController: NavController
    ){
        val gameState = misereViewModel.gameState.value
        LaunchedEffect(Unit) {
            misereViewModel.handleIntent(MisereIntent.GameIntent.ComputerFirstMove)
        }
        GameScreen(
            board = gameState.board,
            currentTurn = gameState.currentTurn,
            gameOver = gameState.gameOver,
            winner = gameState.winner,
            onCellClicked = { row, col ->
                misereViewModel.handleIntent(MisereIntent.GameIntent.CellClicked(row, col))
            },
            onPlayAgain = {
                misereViewModel.handleIntent(MisereIntent.GameIntent.PlayAgain)
            },
            onNavigateToHistory = {
                navController.navigate(HistoryScreenSpec.route)
            },
            onNavigateBack = {
                misereViewModel.handleIntent(MisereIntent.GameIntent.NavigateBack)
                navController.popBackStack()
            }

        )
    }
}
