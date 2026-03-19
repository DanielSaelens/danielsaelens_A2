package com.csci448.danielsaelens.danielsaelens_a2.ui.navigation.specs

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.csci448.danielsaelens.danielsaelens_a2.ui.screens.welcome.WelcomeScreen
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel

object WelcomeScreenSpec : IScreenSpec {
    override val route = "welcome"


    @Composable
    override fun Content(
        modifier: Modifier,
        misereViewModel: MisereViewModel,
        navController: NavController,

    ) {
        val context = LocalContext.current
        WelcomeScreen(
            onPlayClick = {navController.navigate(GameScreenSpec.route)},
            onHistoryClick = {navController.navigate(HistoryScreenSpec.route)},
            onSettingsClick = {navController.navigate(SettingsScreenSpec.route)},
            onQuitClick = {(context as? Activity)?.finish()}
        )
    }
}