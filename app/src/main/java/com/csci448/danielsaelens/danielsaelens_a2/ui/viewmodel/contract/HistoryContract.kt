package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract
import androidx.compose.runtime.State
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.MisereViewModel
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.effect.MisereEffect
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.intent.MisereIntent
import com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.state.HistoryState


class HistoryContract(
    override val state: State<HistoryState>,
    override val effect: State<MisereEffect?>,
    viewModel: MisereViewModel
) : IScreenContract<HistoryState, MisereIntent.HistoryIntent, MisereEffect> {
    override val dispatcher: (MisereIntent.HistoryIntent) -> Unit = { intent ->
        viewModel.handleIntent(intent)
    }
}