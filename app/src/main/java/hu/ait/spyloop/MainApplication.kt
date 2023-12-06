package hu.ait.spyloop

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.HiltAndroidApp
import hu.ait.spyloop.ui.screen.CategoriesScreen
import hu.ait.spyloop.ui.screen.GameScreen
import hu.ait.spyloop.ui.screen.StartScreen
import hu.ait.spyloop.ui.screen.StartViewModel

@HiltAndroidApp
class MainApplication : Application() {
    @Composable
    fun App() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                StartScreen(startViewModel = hiltViewModel(), navController = navController)
            }

            composable("categoriesScreen/{playerNames}") { backStackEntry ->
                val playerNames = backStackEntry.arguments?.getString("playerNames") ?: ""
                val startViewModel = hiltViewModel<StartViewModel>()

                CategoriesScreen(
                    startViewModel = startViewModel,
                    csplayerNames = playerNames,
                    navController = navController
                )
            }

            composable("gameScreen/{categoryName}/{playerNames}") { backStackEntry ->
                val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                val playerNames = backStackEntry.arguments?.getString("playerNames") ?: ""
                val startViewModel = hiltViewModel<StartViewModel>()

                GameScreen(
                    startViewModel = startViewModel,
                    categoryName = categoryName,
                    csplayerNames = playerNames
                )
            }


        }
    }
}
