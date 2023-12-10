package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.data.Player

@Composable
fun AssignmentScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    categoryName: String,
    playerName: String,
    currentPlayerIndex: Int,
    onNavigateToConfirmationScreen: (String, Int) -> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val updatedPlayerIndex = currentPlayerIndex + 1
        val players = startViewModel.getAllPlayers()

        fun getPlayer(players: List<Player>): Player? {
            for (player in players)
                if (player.name == playerName) {
                    return player
                }
            return null
        }

        val player = getPlayer(players)

        Text(text = "Hi $playerName!", fontSize = 24.sp)

        if (player?.outOfLoop!!){
            Text(text = "You are out of the loop.", fontSize = 20.sp)
        }
        else{
            Text(text = "The secret word is: $categoryName", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {onNavigateToConfirmationScreen(categoryName, updatedPlayerIndex)}) {
            Text(text = "Next player")
        }
    }
}