package hu.ait.spyloop.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesScreen(
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) { Text(text = "Welcome to Categories Screen") }
}
