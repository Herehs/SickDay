package com.example.up.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.up.presentation.main_screen.MainScreen
import kotlinx.serialization.Serializable



sealed class Screen(){
    @Serializable
    object MainScreen : Screen()

}


@Composable
fun NavRoot(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen
    ){
        composable<Screen.MainScreen> {
            MainScreen()
        }
    }
}