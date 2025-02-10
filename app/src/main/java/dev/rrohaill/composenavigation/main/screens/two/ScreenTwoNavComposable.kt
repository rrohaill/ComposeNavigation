package dev.rrohaill.composenavigation.main.screens.two

import androidx.navigation.toRoute
import dev.rrohaill.composenavigation.destination.ListData
import dev.rrohaill.composenavigation.destination.ScreenListDestination
import dev.rrohaill.composenavigation.destination.ScreenTwoDestination
import dev.rrohaill.composenavigation.destination.ScreenTwoDestinationContract
import dev.rrohaill.composenavigation.util.LocalNavController
import dev.rrohaill.composenavigation.util.MyNavGraphBuilder
import dev.rrohaill.composenavigation.util.composable
import dev.rrohaill.composenavigation.util.popBackWithResult

fun MyNavGraphBuilder.screenTwoNavComposable() {
    composable(ScreenTwoDestinationContract) { navBackStackEntry ->
        val navData = navBackStackEntry.toRoute<ScreenTwoDestination>()
        val navController = LocalNavController.current
        ScreenTwo(
            data = navData,
            navigateToScreenList = {
                navController.navigate(
                    ScreenListDestination(
                        list = listOf(
                            ListData("I am item 1"),
                            ListData("I am item 2")
                        )
                    )
                )
            },
            goBack = navController::navigateUp,
            goBackWithResult = { result ->
                navController.popBackWithResult(result)
            }
        )
    }
}