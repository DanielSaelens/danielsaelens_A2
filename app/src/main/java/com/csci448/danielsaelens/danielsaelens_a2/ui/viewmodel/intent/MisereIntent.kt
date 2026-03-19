package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.intent

sealed class MisereIntent {
    sealed class WelcomeIntent : MisereIntent() {
        object NavigateToGame : WelcomeIntent()
        object NavigateToHistory : WelcomeIntent()
        object NavigateToSettings : WelcomeIntent()
        object Quit : WelcomeIntent()
    }
    sealed class SettingsIntent : MisereIntent() {
        object ClearHistory: SettingsIntent()
        class ChangeNumPlayers(val numPlayers: Int) : SettingsIntent()
        class ChangeCompDifficulty(val compDifficulty: String) : SettingsIntent()
        class ChangeGoesFirst(val whoFirst: String) : SettingsIntent()
        class ChangeTheme(val theme: String) : SettingsIntent()

    }
    sealed class HistoryIntent : MisereIntent() {
        object NavigateBack : HistoryIntent()
    }
    sealed class GameIntent : MisereIntent() {
        class CellClicked(val row: Int, val col: Int) : GameIntent()
        object PlayAgain: GameIntent()
        object NavigateBackToHistory: GameIntent()
        object NavigateBack: GameIntent()
        object ComputerFirstMove : GameIntent()
    }

}


