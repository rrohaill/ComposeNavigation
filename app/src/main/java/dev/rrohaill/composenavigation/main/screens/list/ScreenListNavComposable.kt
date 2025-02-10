package dev.rrohaill.composenavigation.main.screens.list

import androidx.navigation.toRoute
import dev.rrohaill.composenavigation.destination.ScreenListDestination
import dev.rrohaill.composenavigation.destination.ScreenListDestinationContract
import dev.rrohaill.composenavigation.destination.ScreenOneDestination
import dev.rrohaill.composenavigation.destination.ScreenOneResult
import dev.rrohaill.composenavigation.util.LocalNavController
import dev.rrohaill.composenavigation.util.MyNavGraphBuilder
import dev.rrohaill.composenavigation.util.composable
import dev.rrohaill.composenavigation.util.popBackToDestinationWithResult

fun MyNavGraphBuilder.screenListNavComposable() {
    composable(ScreenListDestinationContract) { navBackStackEntry ->
        val navData = navBackStackEntry.toRoute<ScreenListDestination>()
        val navController = LocalNavController.current
        ScreenList(
            data = navData.list,
            navigateToOne = {
                navController.popBackStack<ScreenOneDestination>(inclusive = false)
            },
            goBack = navController::navigateUp,
            navigateToOneWithResult = { result ->
                navController.popBackToDestinationWithResult<ScreenOneResult, ScreenOneDestination>(
                    result = result
                )
            }
        )
    }
}