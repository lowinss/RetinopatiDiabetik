package com.example.retinopati.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.retinopati.InitialScreen
import com.example.retinopati.core.presentation.screen.home.HomeContent
import com.example.retinopati.core.presentation.screen.information.InformationContent
import com.example.retinopati.core.presentation.screen.result.ResultContent
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
                onClickInfo = { navController.navigate(Routes.InformationScreen) },
                onClickBack = { navController.navigateUp()}
            )
        }
        composable<Routes.SubmitScreen> {
            SubmitContent(
                onClickProceed = { navController.navigate(Routes.ResultScreen(uri = it)) },
                onClickBack = { navController.navigateUp()}
            )
        }
        composable<Routes.ResultScreen> {
            val args = it.toRoute<Routes.ResultScreen>()
            ResultContent(
                uri = args.uri,
                onClickBack = { navController.navigateUp() }
            )
        }
        composable<Routes.InformationScreen> {
            InformationContent(
//                onClickHome = { navController.navigate(Routes.InitialScreen) },
                onClickBack = { navController.navigateUp()}

            )
        }
    }

}