package hu.ait.spyloop

import android.app.Application
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.HiltAndroidApp
import hu.ait.spyloop.ui.screen.StartScreen

@HiltAndroidApp
class MainApplication : Application() {
    @Composable
    fun App() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                StartScreen()
            }
        }
    }
}
