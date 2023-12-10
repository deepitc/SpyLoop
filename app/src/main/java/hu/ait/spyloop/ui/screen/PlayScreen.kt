package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayScreen(
    onNavigateToVotingScreen: (String) -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( text = "Done playing?",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp))

        Button(onClick = { onNavigateToVotingScreen }) {
            Text(text = "Vote")
        }
    }
}
