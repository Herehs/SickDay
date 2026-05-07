package com.example.up.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.up.R
import com.example.up.presentation.common_сomponents.Background
import com.example.up.presentation.common_сomponents.BottomBar
import com.example.up.presentation.common_сomponents.BottomBarItem
import com.example.up.presentation.screens.calendar_screen.CalendarScreen
import com.example.up.presentation.screens.main_screen.MainScreen
import com.example.up.presentation.screens.note_screen.NoteScreen


@Composable
fun NavRoot(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                bottomBarItems = listOf(
                    BottomBarItem(
                        icon = R.drawable.calendar,
                        route = Routes.MainScreen
                    ),
                    BottomBarItem(
                        icon = R.drawable.open_eye,
                        route = Routes.CalendarScreen
                    ),
                    BottomBarItem(
                        icon = R.drawable.pencil,
                        route = Routes.Notes
                    ),
                    BottomBarItem(
                        icon = R.drawable.gear,
                        route = Routes.Settings
                    )
                ),
                onItemClick = {
                    navController.navigate(it)
                }
            )
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Routes.MainScreen,
            enterTransition = { fadeIn(animationSpec = tween(400)) },
            exitTransition = { fadeOut(animationSpec = tween(400)) }
        ){
            composable<Routes.MainScreen> {
                MainScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            composable<Routes.CalendarScreen> {
                CalendarScreen(
                    modifier = Modifier.padding(paddingValues),
                )
            }
            composable<Routes.Notes>{
                NoteScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            composable<Routes.Settings>{
                Background()
            }
        }
    }
}