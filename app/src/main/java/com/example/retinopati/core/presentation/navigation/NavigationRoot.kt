package com.example.retinopati.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.retinopati.InitialScreen
import com.example.retinopati.core.presentation.screen.home.HomeContent
import com.example.retinopati.core.presentation.screen.information.InformationContent
import com.example.retinopati.core.presentation.screen.submit.SubmitContent

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.InitialScreen
    ) {
        composable<Routes.InitialScreen> {
            InitialScreen(onClickStart = { navController.navigate(Routes.HomeScreen) })
        }
        composable<Routes.HomeScreen> {
            HomeContent(
                onClickCheck = { navController.navigate(Routes.SubmitScreen) },
                onClickInfo = { navController.navigate(Routes.InformationScreen) }
            )
        }
        composable<Routes.SubmitScreen> {
            SubmitContent(
                onClickProceed = { navController.navigate(Routes.InformationScreen) }
            )
        }
        composable<Routes.InformationScreen> {
            InformationContent(
                onClickHome = { navController.navigate(Routes.InitialScreen) }
            )
        }
    }

}