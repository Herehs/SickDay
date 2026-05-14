package com.example.up.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.up.R
import com.example.up.presentation.common_сomponents.Background
import com.example.up.presentation.common_сomponents.BottomBar
import com.example.up.presentation.common_сomponents.BottomBarItem
import com.example.up.presentation.screens.auth_screen.AuthorisationScreen
import com.example.up.presentation.screens.calendar_screen.CalendarScreen
import com.example.up.presentation.screens.main_screen.MainScreen
import com.example.up.presentation.screens.note_screen.NoteScreen
import com.example.up.presentation.screens.onboarding.Onboarding
import com.example.up.presentation.screens.registration_screen.RegistrationScreen
import com.example.up.presentation.screens.settings_screen.SettingsScreen


@Composable
fun NavRoot(
    navController: NavHostController = rememberNavController()
) {


    val screens = listOf(
        BottomBarItem(
            icon = R.drawable.open_eye,
            route = Routes.MainScreen
        ),
        BottomBarItem(
            icon = R.drawable.calendar,
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
    )
    val screenOrder = listOf(
        Routes.MainScreen,
        Routes.CalendarScreen,
        Routes.Notes,
        Routes.Settings
    )


    var previousIndex by remember { mutableIntStateOf(0) }
    var showBottomBar by rememberSaveable { mutableStateOf(true) }


    Scaffold(
        bottomBar = {
            if(showBottomBar){
                BottomBar(
                    bottomBarItems = screens,
                    onItemClick = {
                        val newIndex = screenOrder.indexOf(it)

                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("direction", newIndex > previousIndex)

                        previousIndex = newIndex


                        navController.navigate(it){
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Background()
        NavHost(
            navController = navController,
            startDestination = Routes.MainScreen,
            enterTransition = {

                val isForward =
                    initialState.savedStateHandle["direction"] ?: true

                if (isForward) {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(400)
                    )
                } else {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(400)
                    )
                }
            },

            exitTransition = {

                val isForward =
                    initialState.savedStateHandle["direction"] ?: true

                if (isForward) {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(400)
                    )
                } else {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(400)
                    )
                }
            }


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
            composable<Routes.Notes> {
                NoteScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            composable<Routes.Settings> {
                SettingsScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            composable<Routes.Registration>(
                enterTransition = { fadeIn(tween(400)) },
                exitTransition = { fadeOut(tween(400)) }
            ) {
                showBottomBar = false
                RegistrationScreen(
                    modifier = Modifier.padding(paddingValues),
                    onRegisterClick = {
                        navController.navigate(Routes.MainScreen)
                        showBottomBar = true
                                      },
                    onAuthButtonClick = { navController.navigate(Routes.Authorisation) }
                )
            }
            composable<Routes.Authorisation>(
                enterTransition = { fadeIn(tween(400)) },
                exitTransition = { fadeOut(tween(400)) }
            ) {
                showBottomBar = false
                AuthorisationScreen(
                    modifier = Modifier.padding(paddingValues),
                    onLoginClick = {
                        navController.navigate(Routes.MainScreen)
                        showBottomBar = true
                                   },
                    onRegisterButtonClick = { navController.navigate(Routes.Registration) }
                )
            }

            composable<Routes.Onboarding>(
                enterTransition = { fadeIn(tween(400)) },
                exitTransition = { fadeOut(tween(400)) }
            ) {
                showBottomBar = false
                Onboarding(
                    modifier = Modifier.padding(paddingValues),
                    onRegisterClick = { navController.navigate(Routes.Registration) },
                    onLoginClick = { navController.navigate(Routes.Authorisation) }
                )
            }
        }
    }
}