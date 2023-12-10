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
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.data.Player

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    startViewModel: StartViewModel = hiltViewModel(),
    onNavigateToCategoriesScreen: () -> Unit

) {
    var showAddPlayerDialog by rememberSaveable { mutableStateOf(false) }
    var showErrorSnackbar by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.onBackground
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = "Spy Loop Game",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                actions = {
                    Text(
                        text = "Clear All",
                        modifier = Modifier.clickable {
                            StartViewModel.clearAllPlayers()
                        }
                    )
                    IconButton(onClick = { showAddPlayerDialog = true }) {
                        Icon(Icons.Filled.AddCircle, null)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (showAddPlayerDialog) {
            AddNewPlayer(
                startViewModel = startViewModel,
                onDialogDismiss = { showAddPlayerDialog = false }
            )
        }

        Column {
            Button(
                onClick = {
                    val players = startViewModel.getAllPlayers()

                    if (players.size > 2) {
                        onNavigateToCategoriesScreen()
                    } else {
                        showErrorSnackbar = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = "Choose Category")
            }
        }

        if (showErrorSnackbar) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    Button(onClick = { showErrorSnackbar = false }) {
                        Text("OK")
                    }
                }
            ) {
                Text("Please add at least three players to begin.")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(startViewModel.getAllPlayers()) {
                PlayerCard(
                    player = it,
                    onRemoveItem = { startViewModel.removePlayer(it) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddNewPlayer(
    startViewModel: StartViewModel = hiltViewModel(),
    onDialogDismiss: () -> Unit = {}
) {
    // will be used when we extract strings
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDialogDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        var playerName by rememberSaveable {
            mutableStateOf("")
        }

        var nameErrorText by rememberSaveable {
            mutableStateOf("")
        }

        var nameErrorState by rememberSaveable {
            mutableStateOf(false)
        }

        fun valid(text: String): Pair<Boolean, String> {
            if (text.isEmpty()) {
                return Pair(true, "Player name cannot be empty.")
            }
            if (text.contains(Regex("[^a-zA-Z ]"))) {
                return Pair(true, "Player name cannot contain numbers.")
            }
            return Pair(false, "")
        }

        fun validName(text: String) {
            val (errorState, errorText) = valid(text)
            nameErrorState = errorState
            nameErrorText = errorText
        }

        Column(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = playerName,
                onValueChange = {
                    playerName = it
                    validName(playerName)
                },
                label = { Text(text = "Enter Player Name") },
                trailingIcon = {
                    if (nameErrorState)
                        Icon(
                            Icons.Filled.Warning,
                            "error",
                            tint = MaterialTheme.colorScheme.error
                        )
                },
                supportingText = {
                    if (nameErrorState) {
                        Text(
                            text = nameErrorText,
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        if (playerName.isNotEmpty() && !nameErrorState) {
                            startViewModel.addPlayer(
                                Player(
                                    playerName,
                                    false,
                                    0
                                )
                            )
                            onDialogDismiss()
                        } else {
                            if (playerName.isEmpty()) {
                                nameErrorState = true
                                nameErrorText = "Player name cannot be empty."
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Composable
fun PlayerCard(
    player: Player,
    onRemoveItem: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                    text = player.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(0.8f)
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onRemoveItem },
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
