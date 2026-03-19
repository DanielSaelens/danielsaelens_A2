package com.csci448.danielsaelens.danielsaelens_a2.ui.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.R
import com.csci448.danielsaelens.danielsaelens_a2.data.GameRecord
import androidx.compose.foundation.lazy.items

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "History", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Player 1 — W: $playerOneWins | L: $playerOneLosses | T: $playerOneTies")
        Text(text = "Player 2 — W: $playerTwoWins | L: $playerTwoLosses | T:   $playerTwoTies ")
        Text(text = "Computer — W: $computerWins | L: $computerLosses | T: $computerTies")
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(gameRecords) { record ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Game #${record.gameNumber}")
                        Text(text = "Mode: ${record.mode}")
                        Text(text = "Result: ${record.result}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateBack) {
            Text(text = "Go Back")
        }
    }
}





