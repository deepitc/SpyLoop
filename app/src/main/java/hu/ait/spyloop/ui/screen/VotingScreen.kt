package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.data.Player

@Composable
fun VotingScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    onNavigateToResultScreen: () -> Unit
) {
    var currentPlayerIndex by rememberSaveable { mutableStateOf(0) }
    val players = startViewModel.getAllPlayers()
    var visible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (currentPlayerIndex < players.size) {
            val currentPlayer = players[currentPlayerIndex]
            Text(text = "Hi ${currentPlayer.name}!", fontSize = 24.sp)
            Text("Choose who you think is Out of the Loop")
            checkBoxVoting(
                players,
                currentPlayerIndex,
                onVote = {
                    currentPlayerIndex++
                }
            )
        }
        if (currentPlayerIndex==players.size) {
            visible = true
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column() {
            if (visible) {
                Button(
                    onClick = {
                        onNavigateToResultScreen()
                    }
                ) {
                    Text("Results")
                }
            }

        }
    }
}

@Composable
fun checkBoxVoting(
    players: List<Player>,
    currentPlayer: Int,
    onVote: () -> Unit
) {
    var selectedPlayerIndex by rememberSaveable { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        players.forEachIndexed { index, player ->
            if (currentPlayer != index) {
                Row {
                    RadioButton(
                        selected = selectedPlayerIndex == index,
                        onClick = {
                            selectedPlayerIndex = index
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Magenta,
                            unselectedColor = Color.Gray
                        )
                    )
                    Text("${player.name}")
                }
            }
        }
    }

    Button(onClick = {

        if (selectedPlayerIndex != null) {
            val checkedPlayer = players[selectedPlayerIndex!!]
            checkedPlayer.votes++
            onVote()
        }
    }) {
        Text(text = "Vote")
    }
}

