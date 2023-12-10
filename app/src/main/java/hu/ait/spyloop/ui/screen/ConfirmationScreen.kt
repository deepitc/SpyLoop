package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.data.Player

@Composable
fun ConfirmationScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    categoryName: String,
    onNavigateToAssignmentScreen: (String, String) -> Unit,
    onNavigateToPlayScreen: () -> Unit
) {
    val players = startViewModel.getAllPlayers()
    val currentPlayerIndex = remember { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentPlayerIndex.value < players.size) {
            val currentPlayer = players[currentPlayerIndex.value]

            Text(
                text = "Is this ${currentPlayer.name}?",
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    onNavigateToAssignmentScreen(categoryName, currentPlayer.name)
                    currentPlayerIndex.value += 1
                }
            ) {
                Text(text = "Igen!")
            }
        } else {
            Text(text = "All roles assigned.")

            Button(
                onClick = { onNavigateToPlayScreen() }
            ) {
                Text(text = "Let's play!")
            }
        }
    }
}



