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
    onNavigateToAssignmentScreen: (String, String, Int) -> Unit,
    onNavigateToPlayScreen: () -> Unit,
    currentPlayerIndex: Int
) {
    val players = startViewModel.getAllPlayers()
    // var currentPlayerIndex = remember { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentPlayerIndex < players.size) {
            val currentPlayer = players[currentPlayerIndex]

            Text(
                text = "Is this ${currentPlayer.name}?",
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // currentPlayerIndex.value += 1
                    onNavigateToAssignmentScreen(categoryName, currentPlayer.name, currentPlayerIndex)
                }
            ) {
                Text(text = "Igen!")
            }
        } else {
            Text( text = "All Roles Assigned",
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp))

            Button(
                onClick = { onNavigateToPlayScreen() }
            ) {
                Text(text = "Let's play!")
            }
        }
    }
}



