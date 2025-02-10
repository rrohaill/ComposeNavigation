package dev.rrohaill.composenavigation.main.screens.one

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.navigation.toRoute
import dev.rrohaill.composenavigation.destination.ListData
import dev.rrohaill.composenavigation.destination.ScreenListDestination
import dev.rrohaill.composenavigation.destination.ScreenOneDestination
import dev.rrohaill.composenavigation.destination.ScreenOneDestinationContract
import dev.rrohaill.composenavigation.destination.ScreenOneResult
import dev.rrohaill.composenavigation.destination.ScreenTwoDestination
import dev.rrohaill.composenavigation.util.LocalNavController
import dev.rrohaill.composenavigation.util.MyNavGraphBuilder
import dev.rrohaill.composenavigation.util.NavResult
import dev.rrohaill.composenavigation.util.composable
import dev.rrohaill.composenavigation.util.onConsumeResult

fun MyNavGraphBuilder.screenOneNavComposable() {
    composable(ScreenOneDestinationContract) { navBackStackEntry ->
        val navData = navBackStackEntry.toRoute<ScreenOneDestination>()
        val navController = LocalNavController.current
        val activity = LocalActivity.current
        ScreenOne(
            name = navData.name,
            navigateToScreenTwo = {
                navController.navigate(
                    ScreenTwoDestination(
                        name = "I am Screen Two",
                        description = "I am description"
                    )
                )
            },
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
            goBack = { activity?.finish() }
        )

        ObserverResult()
    }
}

@Composable
private fun ObserverResult() {
    val navController = LocalNavController.current
    navController.onConsumeResult<ScreenOneResult, ScreenOneDestination> { result ->
        val data = when (result) {
            is NavResult.Cancel -> {
                "Cancelled"
            }

            is NavResult.Error -> {
                result.data?.data
            }

            is NavResult.Ok -> {
                result.data.data
            }
        }
        data?.let {
            Toast.makeText(navController.context, it, Toast.LENGTH_SHORT).show()
        }
    }
}