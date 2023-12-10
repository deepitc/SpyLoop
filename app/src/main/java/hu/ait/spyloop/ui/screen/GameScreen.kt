package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Text as Text1

@Composable
fun GameScreen(
    categoryName: String,
    onNavigateToConfirmationScreen: (String, Int) -> Unit,
    startViewModel: StartViewModel = hiltViewModel()
) {
    val players = startViewModel.getAllPlayers()

    LaunchedEffect(Unit) {
        startViewModel.pickOutOfLoop(players)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text1(
            text = "You've picked $categoryName",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )


        Button(onClick = { onNavigateToConfirmationScreen(categoryName, 0) }) {
            Text1(text = "Get assignments!")
        }
    }
}

