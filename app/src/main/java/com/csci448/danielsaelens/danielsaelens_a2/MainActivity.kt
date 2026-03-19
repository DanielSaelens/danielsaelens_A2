package com.csci448.danielsaelens.danielsaelens_a2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.danielsaelens.danielsaelens_a2.ui.navigation.MisereNavHost
import com.csci448.danielsaelens.danielsaelens_a2.ui.theme.Danielsaelens_A2Theme
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var _viewModel: MisereViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val factory = MisereViewModelFactory(savedInstanceState)
        _viewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]

        setContent {
            Danielsaelens_A2Theme {
                val navController = rememberNavController()
                MisereNavHost(
                    navController = navController,
                    misereViewModel = _viewModel
                )
            }
        }
    }
}

