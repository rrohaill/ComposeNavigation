package dev.rrohaill.composenavigation.util

import android.content.Intent
import android.os.Parcelable
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


const val COMPOSE_RESULT = "compose_result"

typealias NavTransitionScope = AnimatedContentTransitionScope<NavBackStackEntry>

inline fun <reified D : Destination> MyNavGraphBuilder.composable(
    contract: DestinationContract<D>,
    deepLinks: List<DeepLinkData> = emptyList(),
    noinline enterTransition: (NavTransitionScope.() -> EnterTransition?)? = NavTransitionScope::defaultEnterTransition,
    noinline exitTransition: (NavTransitionScope.() -> ExitTransition?)? = NavTransitionScope::defaultExitTransition,
    noinline popEnterTransition: (NavTransitionScope.() -> EnterTransition?)? = NavTransitionScope::defaultPopEnterTransition,
    noinline popExitTransition: (NavTransitionScope.() -> ExitTransition?)? = NavTransitionScope::defaultPopExitTransition,
    noinline content: @Composable() (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit),
) {
    val mappedDeepLinks = deepLinks.mapToComposableNavDeepLinks(contract)
    navGraphBuilder.composable<D>(
        deepLinks = mappedDeepLinks,
        typeMap = contract.typeMap(),
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        content(it)
    }
}

class MyNavGraphBuilder(
    val navGraphBuilder: NavGraphBuilder
)

val LocalNavController =
    staticCompositionLocalOf<NavController> { error("No NavController provided") }

@Composable
fun MyNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    builder: MyNavGraphBuilder.() -> Unit,
) {
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
            enterTransition = { fadeIn(animationSpec = tween(400)) },
            exitTransition = { fadeOut(animationSpec = tween(400)) },
            builder = { builder.invoke(MyNavGraphBuilder(this)) }
        )
    }
}

@Composable
fun NavBackStackEntry.isFromDeepLink(vararg deepLinks: String): Boolean {
    return rememberSaveable(this) {
        val intent = arguments.getCompatParcelableExtra<Intent>(NavController.KEY_DEEP_LINK_INTENT)
        val path = intent?.data?.path
        deepLinks.contains(path)
    }
}

@Throws(IllegalArgumentException::class)
inline fun <T : Parcelable, reified I : Destination> NavController.popBackToDestinationWithResult(result: NavResult<T>): Boolean {
    val backSTack = getBackStackEntry<I>()
    backSTack.savedStateHandle[COMPOSE_RESULT] = result
    return popBackStack<I>(inclusive = false)
}

fun NavTransitionScope.defaultEnterTransition(): EnterTransition =
    slideIntoContainer(SlideDirection.Start)

fun NavTransitionScope.defaultExitTransition(): ExitTransition =
    slideOutOfContainer(SlideDirection.Start)

fun NavTransitionScope.defaultPopEnterTransition(): EnterTransition =
    slideIntoContainer(SlideDirection.End)

fun NavTransitionScope.defaultPopExitTransition(): ExitTransition =
    slideOutOfContainer(SlideDirection.End)
