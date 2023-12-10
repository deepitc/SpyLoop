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

@Composable
fun AssignmentScreen(
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

        Text(text = "Hi $playerName!", fontSize = 24.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {onNavigateToConfirmationScreen(categoryName, updatedPlayerIndex)}) {
            Text(text = "Next player")
        }
    }
}