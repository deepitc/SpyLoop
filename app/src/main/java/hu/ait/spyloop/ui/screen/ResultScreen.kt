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
            val outOfLooper = players.filter { it.votes == maxVotes }

            if (outOfLooper.size == 1 && outOfLooper[0].outOfLoop) {
                Text(text = "You found the player Out of the Loop!")
            } else {
                Text(text = "The player Out of the Loop wins!")
            }

            Text(text = "${outOfLooper[0].name} was Out of the Loop!")
        }
    }
}



