package dev.rrohaill.composenavigation.util

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import dev.rrohaill.composenavigation.ui.theme.ComposeNavigationTheme

fun ComponentActivity.setContentFullScreen(
    parent: CompositionContext? = null,
    content: @Composable () -> Unit
) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    setContent(parent) {
        ComposeNavigationTheme {
            enableEdgeToEdge()
            Box(Modifier.windowInsetsPadding(insetsHandlingDisplayCutout())) {
                content()
            }
        }
    }
}

@Composable
fun insetsHandlingDisplayCutout() = WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal)
