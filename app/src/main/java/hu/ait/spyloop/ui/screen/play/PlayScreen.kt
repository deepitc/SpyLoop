package hu.ait.spyloop.ui.screen.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.ait.spyloop.R

@Composable
fun PlayScreen(
    onNavigateToVotingScreen: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = stringResource(R.string.instructions),
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(8.dp)
                .wrapContentWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.in_instructions),
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(8.dp)
                .wrapContentWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.out_instructions),
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(8.dp)
                .wrapContentWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.done_playing),
            fontSize = 18.sp,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(8.dp)
        )

        Button(onClick = {
            onNavigateToVotingScreen()
        }) {
            Text(text = stringResource(R.string.vote))
        }
    }
}
