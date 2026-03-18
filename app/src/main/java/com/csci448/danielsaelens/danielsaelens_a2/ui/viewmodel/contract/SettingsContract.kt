package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract
import androidx.compose.runtime.State
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.effect.MisereEffect
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.intent.MisereIntent
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.SettingsState

class SettingsContract(
    override val state: State<SettingsState>,
    override val effect: State<MisereEffect?>,
    viewModel: MisereViewModel

) : IScreenContract<SettingsState, MisereIntent.SettingsIntent, MisereEffect>{
    override val dispatcher: (MisereIntent.SettingsIntent) -> Unit = {intent ->
        viewModel.handleIntent(intent)
    }
}


