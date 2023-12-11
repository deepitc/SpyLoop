package hu.ait.spyloop.ui.screen.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.R
import hu.ait.spyloop.data.Player
import hu.ait.spyloop.ui.screen.SpyLoopViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    spyLoopViewModel: SpyLoopViewModel = hiltViewModel(),
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
            color = MaterialTheme.colorScheme.primary
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.spy_loop_game),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                actions = {
                    IconButton(onClick = { showAddPlayerDialog = true }) {
                        Icon(
                            Icons.Filled.AddCircle,
                            null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.clear_all),
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { SpyLoopViewModel.clearAllPlayers() },
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (showAddPlayerDialog) {
            AddNewPlayer(
                spyLoopViewModel = spyLoopViewModel,
                onDialogDismiss = { showAddPlayerDialog = false }
            )
        }

        Column {
            Button(
                onClick = {
                    val players = spyLoopViewModel.getAllPlayers()

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
                Text(text = stringResource(R.string.choose_category))
            }
        }

        if (showErrorSnackbar) {
            Snackbar(
                modifier = Modifier.padding(16.dp),
                action = {
                    Button(onClick = { showErrorSnackbar = false }) {
                        Text(stringResource(R.string.ok))
                    }
                }
            ) {
                Text(stringResource(R.string.please_add_at_least_three_players_to_begin))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(spyLoopViewModel.getAllPlayers()) {
                PlayerCard(
                    player = it,
                    onRemoveItem = { spyLoopViewModel.removePlayer(it) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AddNewPlayer(
    spyLoopViewModel: SpyLoopViewModel = hiltViewModel(),
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
            mutableStateOf(context.getString(R.string.empty_string))
        }

        var nameErrorText by rememberSaveable {
            mutableStateOf(context.getString(R.string.empty_string))
        }

        var nameErrorState by rememberSaveable {
            mutableStateOf(false)
        }

        fun valid(text: String): Pair<Boolean, String> {
            if (text.isEmpty()) {
                return Pair(true, context.getString(R.string.player_name_cannot_be_empty))
            }
            if (text.contains(Regex("[^a-zA-Z ]"))) {
                return Pair(true, context.getString(R.string.player_name_cannot_contain_numbers))
            }
            return Pair(false, context.getString(R.string.empty_string))
        }

        fun validName(text: String) {
            val (errorState, errorText) = valid(text)
            nameErrorState = errorState
            nameErrorText = errorText
        }

        Column(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = playerName,
                onValueChange = {
                    playerName = it
                    validName(playerName)
                },
                label = {
                    Text(
                        text = stringResource(R.string.enter_player_name),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                trailingIcon = {
                    if (nameErrorState)
                        Icon(
                            Icons.Filled.Warning,
                            stringResource(R.string.error),
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
                            spyLoopViewModel.addPlayer(
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
                                nameErrorText =
                                    context.getString(R.string.player_name_cannot_be_empty)
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
                    Text(text = stringResource(R.string.save))
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
        shape = RoundedCornerShape(16.dp),
        colors = cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        )
    ) {

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
                    contentDescription = stringResource(R.string.delete),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onRemoveItem() },
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}
