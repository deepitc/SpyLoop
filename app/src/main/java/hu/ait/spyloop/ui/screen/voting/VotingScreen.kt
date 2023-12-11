package hu.ait.spyloop.ui.screen.voting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.R
import hu.ait.spyloop.data.Player
import hu.ait.spyloop.ui.screen.SpyLoopViewModel

@Composable
fun VotingScreen(
    spyLoopViewModel: SpyLoopViewModel = hiltViewModel(),
    onNavigateToResultScreen: () -> Unit
) {
    var currentPlayerIndex by rememberSaveable { mutableStateOf(0) }
    val players = spyLoopViewModel.getAllPlayers()
    var visible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (currentPlayerIndex < players.size) {
            val currentPlayer = players[currentPlayerIndex]
            Text(
                text = stringResource(R.string.hi, currentPlayer.name),
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.choose_who_you_think_is_out_of_the_loop),
                fontSize = 18.sp,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(8.dp)
            )
            CheckBoxVoting(
                players,
                currentPlayerIndex,
                onVote = {
                    currentPlayerIndex++
                }
            )
        }
        if (currentPlayerIndex == players.size) {
            visible = true
        }

        if (visible) {
            Text(
                text = stringResource(R.string.all_players_have_voted),
                fontSize = 24.sp,
                color = Color.White,
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
                    onNavigateToResultScreen()
                }
            ) {
                Text(stringResource(R.string.see_results))
            }
        }
    }
}

@Composable
fun CheckBoxVoting(
    players: List<Player>,
    currentPlayer: Int,
    onVote: () -> Unit
) {
    var selectedPlayerIndex by rememberSaveable { mutableStateOf<Int?>(null) }

    Column {
        players.forEachIndexed { index, player ->
            if (currentPlayer != index) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedPlayerIndex == index,
                        onClick = {
                            selectedPlayerIndex = index
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.secondary,
                            unselectedColor = MaterialTheme.colorScheme.tertiary
                        )
                    )
                    Text(player.name, fontSize = 20.sp)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {

        if (selectedPlayerIndex != null) {
            val checkedPlayer = players[selectedPlayerIndex!!]
            checkedPlayer.votes++
            onVote()
            selectedPlayerIndex = null
        }
    }) {
        Text(text = stringResource(R.string.vote))
    }
}

