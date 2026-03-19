package com.csci448.danielsaelens.danielsaelens_a2.ui.screens.settings

import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.csci448.danielsaelens.danielsaelens_a2.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun SettingsScreen(
    numPlayers: Int,
    difficulty: String,
    whoGoesFirst: String,
    theme: String,
    onNumPlayersChange: (Int) -> Unit,
    onDifficultyChange: (String) -> Unit,
    onWhoGoesFirstChange: (String) -> Unit,
    onThemeChange: (String) -> Unit,
    onClearHistory: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Number of Players
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(R.string.settings_num_players))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(stringResource(R.string.settings_one_player))
                Switch(
                    checked = numPlayers == 2,
                    onCheckedChange = { isChecked ->
                        onNumPlayersChange(if (isChecked) 2 else 1)
                    }
                )
                Text(stringResource(R.string.settings_two_players))
            }
        }

        // Computer Difficulty (only visible when 1 player)
        if (numPlayers == 1) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(R.string.settings_difficulty))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.settings_easy))
                    Switch(
                        checked = difficulty == "Hard",
                        onCheckedChange = { isChecked ->
                            onDifficultyChange(if (isChecked) "Hard" else "Easy")
                        }
                    )
                    Text(stringResource(R.string.settings_hard))
                }
            }
        }

        if (numPlayers == 1) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(R.string.settings_who_first))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.settings_player_one))
                    Switch(
                        checked = whoGoesFirst == "Computer",
                        onCheckedChange = { isChecked ->
                            onWhoGoesFirstChange(if (isChecked) "Computer" else "Player One")
                        }
                    )
                    Text(stringResource(R.string.settings_computer))
                }
            }
        }

        var expanded by remember { mutableStateOf(false) }
        val themes = listOf("Monsters Inc")

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(R.string.settings_theme))
            Box{
                Button(onClick = {expanded = true}) {
                    Text(theme)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false}
                )
                {
                    themes.forEach { t ->
                        DropdownMenuItem(
                            text = {Text(t) },
                            onClick = {
                                onThemeChange(t)
                                expanded = false
                            }
                        )
                    }
                }

            }
        }
        Button(
            onClick = onClearHistory,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.settings_clear_history))
        }

    }
}


