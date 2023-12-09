package hu.ait.spyloop.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.data.Player

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    categoryName: String,
) {
    //val categoryName = "Food"
/*
    val players = listOf(
        Player("Player 1", false),
        Player("Player 2", false),
        Player("Player 3", true),
    )

 */

    val players = startViewModel.getAllPlayers()
   
    // might want to move this to the categories screen? we can play around with it
    val outOfLooper: Player = startViewModel.pickOutOfLoop(players)

    var currentPlayerIndex by remember { mutableStateOf(0) }

    //val outOfLooper = players[2]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Welcome to Category $categoryName",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "Hello ${players}, this is the GameScreen !",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Text(
            text = "Out of Looper is $outOfLooper",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Confirmation(players[currentPlayerIndex]) {
            if (currentPlayerIndex < players.size - 1) {
                currentPlayerIndex++
            } else {
                // fill in
            }
        }
    }
}

@Composable
fun Confirmation(player: Player, onNavigateToAssignments: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Is this $player?", fontSize = 24.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToAssignments) {
            Text(text = "Yes!")
        }
    }
}



