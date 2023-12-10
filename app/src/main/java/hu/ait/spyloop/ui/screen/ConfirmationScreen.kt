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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.R

@Composable
fun ConfirmationScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    secretWord: String,
    onNavigateToAssignmentScreen: (String, Int) -> Unit,
    onNavigateToPlayScreen: () -> Unit,
    currentPlayerIndex: Int
) {
    val players = startViewModel.getAllPlayers()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentPlayerIndex < players.size) {
            val currentPlayer = players[currentPlayerIndex]

            Text(
                text = stringResource(R.string.is_this, currentPlayer.name),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onNavigateToAssignmentScreen(secretWord, currentPlayerIndex)
                }
            ) {
                Text(text = "Igen!")
            }
        } else {
            Text(
                text = "All Roles Assigned",
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )

            Button(
                onClick = { onNavigateToPlayScreen() }
            ) {
                Text(text = "Let's play!")
            }
        }
    }
}



