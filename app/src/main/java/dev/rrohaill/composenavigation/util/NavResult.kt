package dev.rrohaill.composenavigation.util

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import kotlinx.parcelize.Parcelize

sealed class NavResult<T : Parcelable> : Parcelable {
    @Parcelize
    class Ok<T : Parcelable>(val data: T) : NavResult<T>()

    @Parcelize
    class Cancel<T : Parcelable> : NavResult<T>()

    @Parcelize
    class Error<T : Parcelable>(val data: T? = null) : NavResult<T>()
}

@SuppressLint("ComposableNaming")
@Composable
inline fun <reified R : Parcelable, reified T : Destination> NavController.onConsumeResult(
    crossinline onResult: (NavResult<R>) -> Unit
) {
    if (currentBackStackEntry?.destination?.hasRoute<T>() == true) {
        currentBackStackEntry?.savedStateHandle?.let { savedStateHandle ->
            val result by savedStateHandle.getStateFlow<Parcelable?>(COMPOSE_RESULT, null)
                .collectAsState()
            LaunchedEffect(result) {
                result?.also {
                    try {
                        val returnResult = when (val castResult = result as NavResult<*>) {
                            is NavResult.Cancel -> NavResult.Cancel()
                            is NavResult.Error -> NavResult.Error(castResult.data as? R?)
                            is NavResult.Ok -> NavResult.Ok(castResult.data as R)
                        }
                        onResult(returnResult)
                        savedStateHandle.remove<Parcelable?>(COMPOSE_RESULT)
                    } catch (_: Throwable) {
                        onResult(NavResult.Error())
                    }
                }
            }
        }
    }
}

fun <T : Parcelable> NavController.popBackWithResultFromCurrentDestination(
    result: NavResult<T>,
    currentNavBackStackEntry: NavBackStackEntry
) {
    if (this.currentBackStackEntry == currentNavBackStackEntry) {
        popBackWithResult(result)
    }
}


fun <T : Parcelable> NavController.popBackWithResult(result: NavResult<T>) {
    setResult(result)
    popBackStack()
}


fun <T : Parcelable> NavController.setResult(result: NavResult<T>) {
    previousBackStackEntry?.savedStateHandle?.set(COMPOSE_RESULT, result)
}