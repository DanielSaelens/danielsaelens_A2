package com.csci448.danielsaelens.danielsaelens_a2.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.danielsaelens.danielsaelens_a2.ui.screens.settings.SettingsScreen
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.intent.MisereIntent
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.SettingsState


object SettingsScreenSpec : IScreenSpec {

    override val route = "settings"

    @Composable
    override fun Content(
        modifier: Modifier,
        misereViewModel: MisereViewModel,
        navController: NavController
    ) {
        val contract = misereViewModel.settingsContract.use()

        SettingsScreen(
            numPlayers = contract.state.numPlayers,
            difficulty = contract.state.difficulty,
            whoGoesFirst = contract.state.whoGoesFirst,
            theme = contract.state.theme,
            onNumPlayersChange = {
                contract.dispatcher.invoke(MisereIntent.SettingsIntent.ChangeNumPlayers(it))
            },
            onDifficultyChange = {
                contract.dispatcher.invoke(MisereIntent.SettingsIntent.ChangeCompDifficulty(it))
            },
            onWhoGoesFirstChange = {
                contract.dispatcher.invoke(MisereIntent.SettingsIntent.ChangeGoesFirst(it))
            },
            onThemeChange = {
                contract.dispatcher.invoke(MisereIntent.SettingsIntent.ChangeTheme(it))
            },
            onClearHistory = {
                contract.dispatcher.invoke(MisereIntent.SettingsIntent.ClearHistory)
            },
            onNavigateBack = { navController.popBackStack() }



        )
    }
}