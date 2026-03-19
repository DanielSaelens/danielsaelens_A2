package com.csci448.danielsaelens.danielsaelens_a2.ui.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.csci448.danielsaelens.danielsaelens_a2.ui.navigation.specs.IScreenSpec
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel

@Composable
fun MisereNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    misereViewModel: MisereViewModel
) {
    NavHost(
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination

        ) {
            IScreenSpec.allScreens.forEach { screen ->
                if(screen != null){
                    composable(route = screen.route){
                        screen.Content(
                            modifier = modifier,
                            misereViewModel = misereViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

}