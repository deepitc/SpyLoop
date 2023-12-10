package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.R
import hu.ait.spyloop.ui.screen.StartViewModel.Companion.resetOutOfLooper

@Composable
fun ResultScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    onNavigateToStartScreen: () -> Unit
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
                Text(
                    text = stringResource(R.string.you_found_the_player_out_of_the_loop),
                    fontSize = 27.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(8.dp)
                )
            } else {
                Text(
                    text = stringResource(R.string.the_player_out_of_the_loop_wins),
                    textAlign = TextAlign.Center,
                    fontSize = 27.sp,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.was_out_of_the_loop, outOfLooper[0].name),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            resetOutOfLooper()
            onNavigateToStartScreen()
        }) {
            Text(text = stringResource(R.string.restart_game))
        }
    }
}



