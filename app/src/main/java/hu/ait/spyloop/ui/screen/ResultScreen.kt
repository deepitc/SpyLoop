package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.data.Player

@Composable
fun ResultScreen(
    startViewModel: StartViewModel = hiltViewModel()
) {
    val players = startViewModel.getAllPlayers()

    // Initialize playerVotes with zeros
    var playerVotes by rememberSaveable { mutableStateOf(List(players.size) { 0 }) }

    // Update playerVotes with actual votes
    players.forEachIndexed { index, player ->
        playerVotes = playerVotes.toMutableList().also {
            it[index] = player.votes
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val maxVotes = playerVotes.maxOrNull()

        if (maxVotes != null) {
            val winners = players.filter { it.votes == maxVotes }

            if (winners.size == 1 && winners[0].outOfLoop) {
                Text(text = "You win!")
            } else {
                Text(text = "You lose!")
            }
        }
    }
}



