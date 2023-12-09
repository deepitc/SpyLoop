package hu.ait.spyloop.ui.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hu.ait.spyloop.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToStartScreen: () -> Unit) = Box(
    Modifier
        .fillMaxSize()
) {
    val scale = remember {
        Animatable(0.0f)
    }
    LaunchedEffect(key1 = Unit) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            })
        )
        // 3 second delay then navigate to main screen
        delay(3000)
        onNavigateToStartScreen()
    }
    Image(
        painter = painterResource(id = R.drawable.launch_icon),
        contentDescription = "splash icon",
        alignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .scale(scale.value)
    )
}