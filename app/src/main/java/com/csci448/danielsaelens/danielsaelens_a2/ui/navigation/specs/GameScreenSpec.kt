package com.csci448.danielsaelens.danielsaelens_a2.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.danielsaelens.danielsaelens_a2.ui.screens.game.GameScreen
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel

object GameScreenSpec : IScreenSpec{
    override val route = "game"
    @Composable
    override fun Content(
        modifier: Modifier,
        misereViewModel: MisereViewModel,
        navController: NavController
    ){
        GameScreen()
    }
}
