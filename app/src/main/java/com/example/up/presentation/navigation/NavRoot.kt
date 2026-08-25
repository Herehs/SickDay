package com.example.up.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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
import com.example.up.presentation.screens.onboarding.Onboarding


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
    )
    val screenOrder = listOf(
        Routes.MainScreen,
        Routes.CalendarScreen,
        Routes.Notes,
    )

    var previousIndex by remember { mutableIntStateOf(0) }
    var showBottomBar by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            val isEditingOnboarding = destination.route?.contains("Onboarding") == true
            showBottomBar = !isEditingOnboarding
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(
                    animationSpec = tween(300),
                    initialOffsetY = { y -> y }
                ),
                exit = slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = { y -> y }
                )
            ) {
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
            startDestination = Routes.Onboarding,
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
            composable<Routes.Onboarding>(
                enterTransition = { fadeIn(tween(400)) },
                exitTransition = { fadeOut(tween(400)) }
            ) {
                Onboarding(
                    modifier = Modifier,
                    onButtonClick = {
                        navController.navigate(Routes.MainScreen){
                            popUpTo<Routes.Onboarding> {
                                inclusive = true
                            }
                        }
                    },
                )
            }
        }
    }
}