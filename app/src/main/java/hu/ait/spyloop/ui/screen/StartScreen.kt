package hu.ait.spyloop.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hu.ait.spyloop.data.PlayerName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    navController: NavController? = null
) {
    var showAddPlayerDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("Spy Loop Game") },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            actions = {
                IconButton(onClick = { showAddPlayerDialog = true }) {
                    Icon(Icons.Filled.AddCircle, null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (showAddPlayerDialog) {
            AddNewPlayer(
                startViewModel = startViewModel,
                onDialogDismiss = { showAddPlayerDialog = false }
            )
        }

        Row {
            Text(text = "Welcome")
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                navController?.navigate("categoriesScreen")
            }) { Text(text = "Choose Category") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(startViewModel.playerNames) { playerName ->
                PlayerCard(
                    playerName = playerName,
                    onRemoveItem = { removedPlayer -> startViewModel.removePlayer(removedPlayer) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddNewPlayer(
    startViewModel: StartViewModel,
    onDialogDismiss: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDialogDismiss
    ) {
        var playerName by rememberSaveable { mutableStateOf("") }
        var showError by rememberSaveable { mutableStateOf(false) }

        Column(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = playerName,
                onValueChange = {
                    playerName = it
                    showError = false
                },
                label = { Text(text = "Enter Player Name here:") },
                isError = showError
            )

            if (showError) {
                Spacer(modifier = Modifier.height(8.dp))
                if (playerName.trim().isEmpty()){
                    Text(
                        text = "Player name cannot be empty",
                        color = Color.Red
                    )
                }
                else {
                    Text(
                        text = "Player name cannot contain numbers",
                        color = Color.Red
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    if (playerName.trim().isNotEmpty() && !playerName.any { it.isDigit() }) {
                        startViewModel.addPlayer(PlayerName(playerName))
                        onDialogDismiss()
                    } else {
                        showError = true
                    }
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Composable
fun PlayerCard(
    playerName: PlayerName,
    onRemoveItem: (PlayerName) -> Unit = {},
    onCityClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCityClick() },
        shape = RoundedCornerShape(16.dp)
    ) {
        var expanded by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = playerName.playerName,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(0.8f)
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onRemoveItem(playerName) },
                    tint = Color.Red
                )
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (expanded) "Less" else "More"
                    )
                }
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Press to Learn More",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}