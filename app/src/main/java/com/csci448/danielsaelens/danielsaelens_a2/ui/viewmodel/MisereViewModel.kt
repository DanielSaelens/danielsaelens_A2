package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.csci448.danielsaelens.danielsaelens_a2.data.GameRecord
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
        when (intent) {
            is MisereIntent.GameIntent.CellClicked -> {
                val row = intent.row
                val col = intent.col
                val current = _gameState.value


                // Ignore if game over or cell taken
                if (current.gameOver || current.board[row][col].isNotEmpty()) return

                // Place piece
                val newBoard = current.board.mapIndexed { r, rowList ->
                    rowList.mapIndexed { c, cell ->
                        if (r == row && c == col) current.currentTurn else cell
                    }
                }

                // Check win/tie
                val loser = checkLoser(newBoard, current.currentTurn)
                val isTie = loser == null && newBoard.all { r -> r.all { it.isNotEmpty() } }
                val gameOver = loser != null || isTie
                val winner = if (loser != null) {
                    // In misère, the one who makes 4-in-a-row LOSES, so other player wins
                    if (current.currentTurn == "Player One") "Computer" else "Player One"
                } else null

                // Next turn
                val nextTurn = if (current.currentTurn == "Player One")
                    _settingsState.value.whoGoesFirst.let {
                        if (_settingsState.value.numPlayers == 2) "Player Two" else "Computer"
                    }
                else "Player One"

                _gameState.value = current.copy(
                    board = newBoard,
                    currentTurn = if (gameOver) current.currentTurn else nextTurn,
                    gameOver = gameOver,
                    winner = if (isTie) null else winner
                )
                if (_settingsState.value.numPlayers == 1 && !gameOver) {
                    makeComputerMove()
                }

                // Save to history
                if (gameOver) saveGameToHistory(winner)
            }

            is MisereIntent.GameIntent.PlayAgain -> {
                _gameState.value = GameState(
                    currentTurn = _settingsState.value.whoGoesFirst
                )
                if (_settingsState.value.whoGoesFirst == "Computer") {
                    makeComputerMove()
                }
            }

            is MisereIntent.GameIntent.NavigateBackToHistory -> { }
            is MisereIntent.GameIntent.NavigateBack -> { }
            is MisereIntent.GameIntent.ComputerFirstMove -> {
                if (_settingsState.value.whoGoesFirst == "Computer") {
                    makeComputerMove()
                }
            }
        }

    }

    private fun checkLoser(board: List<List<String>>, player: String): String? {
        // Check rows
        for (row in board) {
            for (col in 0..0) {
                if ((0..3).all { row[col + it] == player }) return player
            }
        }
        // Check columns
        for (col in 0..3) {
            for (row in 0..0) {
                if ((0..3).all { board[row + it][col] == player }) return player
            }
        }
        // Check diagonals
        if ((0..3).all { board[it][it] == player }) return player
        if ((0..3).all { board[it][3 - it] == player }) return player

        return null
    }

    private fun saveGameToHistory(winner: String?) {
        val history = _historyState.value
        val gameNumber = history.gameRecords.size + 1
        val mode = if (_settingsState.value.numPlayers == 1)
            "One Player (${_settingsState.value.difficulty})" else "Two Player"
        val result = winner ?: "Tie"

        val newRecord = GameRecord(
            gameNumber = gameNumber,
            mode = mode,
            result = result
        )

        _historyState.value = history.copy(
            gameRecords = history.gameRecords + newRecord,
            playerOneWins = history.playerOneWins + if (winner == "Player One") 1 else 0,
            playerOneLosses = history.playerOneLosses + if (winner == "Computer" || winner == "Player Two") 1 else 0,
            playerOneTies = history.playerOneTies + if (winner == null) 1 else 0,
            computerWins = history.computerWins + if (winner == "Computer") 1 else 0,
            computerLosses = history.computerLosses + if (winner == "Player One") 1 else 0,
            computerTies = history.computerTies + if (winner == null) 1 else 0,
            playerTwoWins = history.playerTwoWins + if (winner == "Player Two") 1 else 0,
            playerTwoLosses = history.playerTwoLosses + if (winner == "Player One") 1 else 0,
            playerTwoTies = history.playerTwoTies + if (winner == null) 1 else 0
        )
    }
    private fun makeComputerMove() {
        val current = _gameState.value
        if (current.gameOver) return

        val difficulty = _settingsState.value.difficulty
        val emptyCells = mutableListOf<Pair<Int, Int>>()
        for (row in 0..3) {
            for (col in 0..3) {
                if (current.board[row][col].isEmpty()) emptyCells.add(Pair(row, col))
            }
        }
        if (emptyCells.isEmpty()) return

        val move = if (difficulty == "Hard") {
            // Try to find a safe move that doesn't create 4-in-a-row for computer
            val safeMoves = emptyCells.filter { (row, col) ->
                val testBoard = current.board.mapIndexed { r, rowList ->
                    rowList.mapIndexed { c, cell ->
                        if (r == row && c == col) "Computer" else cell
                    }
                }
                checkLoser(testBoard, "Computer") == null
            }
            // Pick from safe moves, otherwise pick random
            if (safeMoves.isNotEmpty()) safeMoves.random() else emptyCells.random()
        } else {
            // Easy — pure random
            emptyCells.random()
        }

        val (row, col) = move
        val newBoard = current.board.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, cell ->
                if (r == row && c == col) "Computer" else cell
            }
        }

        val loser = checkLoser(newBoard, "Computer")
        val isTie = loser == null && newBoard.all { r -> r.all { it.isNotEmpty() } }
        val gameOver = loser != null || isTie
        val winner = if (loser != null) "Player One" else null

        _gameState.value = current.copy(
            board = newBoard,
            currentTurn = if (gameOver) "Computer" else "Player One",
            gameOver = gameOver,
            winner = if (isTie) null else winner
        )

        if (gameOver) saveGameToHistory(winner)
    }


}