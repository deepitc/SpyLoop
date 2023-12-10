package hu.ait.spyloop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import hu.ait.spyloop.data.Player
import hu.ait.spyloop.ui.screen.AssignmentScreen
import hu.ait.spyloop.ui.screen.CategoriesScreen
import hu.ait.spyloop.ui.screen.ConfirmationScreen
import hu.ait.spyloop.ui.screen.GameScreen
import hu.ait.spyloop.ui.screen.PlayScreen
import hu.ait.spyloop.ui.screen.SplashScreen
import hu.ait.spyloop.ui.screen.StartScreen
import hu.ait.spyloop.ui.theme.SpyLoopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpyLoopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost()
                }
            }
        }
    }
}

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "splashscreen"
) {
    androidx.navigation.compose.NavHost(
        navController = navController, startDestination = startDestination
    )
    {
        composable("splashscreen") {
            SplashScreen(
                onNavigateToStartScreen = {
                    navController.navigate("startscreen")
                })
        }
        composable("startscreen") {
            StartScreen(
                onNavigateToCategoriesScreen = {
                    navController.navigate("categoriesscreen")
                }
            )
        }
        composable("categoriesscreen") {
            CategoriesScreen(
                onNavigateToGameScreen = { category ->
                    navController.navigate("gamescreen/$category")
                }
            )
        }
        composable(
            "gamescreen/{category}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
            )
        ) {
            val category = it.arguments?.getString("category")
            GameScreen(
                categoryName = category!!,
                onNavigateToConfirmationScreen = {
                        category -> navController.navigate("confirmationscreen/$category")
                }
            )
        }

        composable("confirmationscreen/{category}",
            arguments = listOf(
                navArgument("category"){type = NavType.StringType},
            )
        ) {
            val category = it.arguments?.getString("category")
            ConfirmationScreen(
                categoryName = category!!,
                onNavigateToAssignmentScreen = {
                        category, playerName -> navController.navigate("assignmentscreen/$category/$playerName")
                },
                onNavigateToPlayScreen = {
                    navController.navigate("playscreen")
                }
            )
        }

        composable("assignmentscreen/{category}/{playerName}",
            arguments = listOf(
                navArgument("category"){type = NavType.StringType},
                navArgument("playerName"){type = NavType.StringType},
            )
        ) {
            val category = it.arguments?.getString("category")
            val playerName = it.arguments?.getString("playerName")
            AssignmentScreen(
                categoryName = category!!,
                playerName = playerName!!,
                onNavigateToConfirmationScreen = {
                        category -> navController.navigate("confirmationscreen/$category")
                }
            )
        }

        composable("playscreen"){
            PlayScreen(
                onNavigateToVotingScreen = {
                    navController.navigate("votingscreen")
                }
            )
        }
    }
}

