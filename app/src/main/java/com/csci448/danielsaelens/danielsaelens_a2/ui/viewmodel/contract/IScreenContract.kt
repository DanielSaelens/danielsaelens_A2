package com.csci448.danielsaelens.danielsaelens_a2.ui.viewmodel.contract
import androidx.compose.runtime.State


// NOTE: We able to reference the quizler app for this code!
// IScreenSpec defines the contract that every screen in the app must follow.
// Each screen provides a unique route (used by the NavGraph to navigate to it)
// and a Content function (the actual Composable that draws the screen).
// The ViewModel and NavController are passed in so each screen can read state
// and trigger navigation.
sealed interface IScreenContract<STATE, INTENT, EFFECT> {
    val state: State<STATE>
    val dispatcher: (INTENT) -> Unit
    val effect: State<EFFECT?>
    data class StateDispatchEffect<STATE, INTENT, EFFECT>(
        val state: STATE,
        val dispatcher: (INTENT) -> Unit,
        val effect: EFFECT?
    )

    fun use() = StateDispatchEffect(
        state = state.value,
        dispatcher = dispatcher,
        effect = effect.value

    )


}