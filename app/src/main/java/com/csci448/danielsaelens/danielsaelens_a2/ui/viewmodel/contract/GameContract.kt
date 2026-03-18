package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract
import androidx.compose.runtime.State
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.effect.MisereEffect
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.intent.MisereIntent
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.GameState

class GameContract(
    override val state: State<GameState>,
    override val effect: State<MisereEffect?>    ,
    viewModel: MisereViewModel
) : IScreenContract<GameState, MisereIntent.GameIntent, MisereEffect>{
    override val dispatcher: (MisereIntent.GameIntent) -> Unit = {intent ->
        viewModel.handleIntent(intent)
    }
}




