package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.R

@Composable
fun AssignmentScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    secretWord: String,
    currentPlayerIndex: Int,
    onNavigateToConfirmationScreen: (String, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val updatedPlayerIndex = currentPlayerIndex + 1
        val players = startViewModel.getAllPlayers()
        val player = players[currentPlayerIndex]

        Text(
            text = stringResource(R.string.hi, player.name),
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (player.outOfLoop) {
            Text(
                text = stringResource(R.string.you_are_out_of_the_loop),
                fontSize = 20.sp,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(8.dp)
            )
        } else {
            Text(
                text = stringResource(R.string.the_secret_word_is, secretWord),
                fontSize = 20.sp,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onNavigateToConfirmationScreen(secretWord, updatedPlayerIndex) }) {
            Text(text = stringResource(R.string.next_player))
        }
    }
}