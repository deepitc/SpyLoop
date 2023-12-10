package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel

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



