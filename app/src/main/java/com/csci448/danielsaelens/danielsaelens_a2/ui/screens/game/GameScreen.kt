package com.csci448.danielsaelens.danielsaelens_a2.ui.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxHeight
import com.csci448.danielsaelens.danielsaelens_a2.R


@Composable
fun GameScreen(
    board: List<List<String>>,
    currentTurn: String,
    gameOver: Boolean,
    winner: String?,
    onCellClicked: (Int, Int) -> Unit,
    onPlayAgain: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Grid on the left
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                for (row in 0..3) {
                    Row {
                        for (col in 0..3) {
                            Box(
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(4.dp)
                                    .border(2.dp, MaterialTheme.colorScheme.primary)
                                    .clickable(enabled = !gameOver && board[row][col].isEmpty()) {
                                        onCellClicked(row, col)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                when (board[row][col]) {
                                    "Player One" -> Image(
                                        painter = painterResource(id = R.drawable.mike),
                                        contentDescription = "Mike",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                    "Player Two", "Computer" -> Image(
                                        painter = painterResource(id = R.drawable.sully),
                                        contentDescription = "Sulley",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Status + buttons on the right
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = if (gameOver) "Game Over!" else "$currentTurn's Turn",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (gameOver) {
                    Text(
                        text = if (winner != null) "$winner Wins!" else "It's a Tie!",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onPlayAgain, modifier = Modifier.fillMaxWidth(0.6f)) {
                        Text("Play Again")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onNavigateToHistory, modifier = Modifier.fillMaxWidth(0.6f)) {
                        Text("View History")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth(0.6f)) {
                    Text("Go Back")
                }
            }
        }
    } else {
        // Portrait
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (gameOver) "Game Over!" else "$currentTurn's Turn",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (gameOver) {
                Text(
                    text = if (winner != null) "$winner Wins!" else "It's a Tie!",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            for (row in 0..3) {
                Row {
                    for (col in 0..3) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .padding(4.dp)
                                .border(2.dp, MaterialTheme.colorScheme.primary)
                                .clickable(enabled = !gameOver && board[row][col].isEmpty()) {
                                    onCellClicked(row, col)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            when (board[row][col]) {
                                "Player One" -> Image(
                                    painter = painterResource(id = R.drawable.mike),
                                    contentDescription = "Mike",
                                    modifier = Modifier.fillMaxSize()
                                )
                                "Player Two", "Computer" -> Image(
                                    painter = painterResource(id = R.drawable.sully),
                                    contentDescription = "Sulley",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (gameOver) {
                Button(onClick = onPlayAgain, modifier = Modifier.fillMaxWidth()) {
                    Text("Play Again")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onNavigateToHistory, modifier = Modifier.fillMaxWidth()) {
                    Text("View History")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
                Text("Go Back")
            }
        }
    }
}

