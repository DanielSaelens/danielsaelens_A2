package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract
import androidx.compose.runtime.State
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.effect.MisereEffect
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.intent.MisereIntent
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.WelcomeState

class WelcomeContract(
    override val state: State<WelcomeState>,
    override val effect: State<MisereEffect?>,
    viewModel: MisereViewModel
) : IScreenContract<WelcomeState, MisereIntent.WelcomeIntent, MisereEffect>{
    override val dispatcher: (MisereIntent.WelcomeIntent) -> Unit = { intent ->
        viewModel.handleIntent(intent)
    }
}

