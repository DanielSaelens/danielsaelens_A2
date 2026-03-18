package com.csci448.danielsaelens.danielsaelens_a2.ui.navigation.specs
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel

sealed interface IScreenSpec {
    val route: String

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        misereViewModel: MisereViewModel,
        navController: NavController
    )
    companion object{
        val allScreens = IScreenSpec::class.sealedSubclasses.map { it.objectInstance }
        val root = "misere"
        val startDestination = WelcomeScreenSpec.route

    }
}




