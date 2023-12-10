package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hu.ait.spyloop.R
import androidx.compose.material3.Text as Text1

@Composable
fun GameScreen(
    categoryName: String,
    onNavigateToConfirmationScreen: (String, Int) -> Unit,
    categoriesViewModel: CategoriesViewModel = hiltViewModel(),
    startViewModel: StartViewModel = hiltViewModel()
) {
    val players = startViewModel.getAllPlayers()

    var secretWord by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        startViewModel.pickOutOfLoop(players)
        secretWord = categoriesViewModel.getWord(categoryName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text1(
            text = stringResource(R.string.you_ve_picked) + "\n" + categoryName,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                )
        )

        Button(onClick = { onNavigateToConfirmationScreen(secretWord, 0) }) {
            Text1(text = stringResource(R.string.get_assignments))
        }
    }
}
