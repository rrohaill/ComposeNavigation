package dev.rrohaill.composenavigation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.rrohaill.composenavigation.destination.ScreenOneDestination
import dev.rrohaill.composenavigation.main.screens.one.screenOneNavComposable
import dev.rrohaill.composenavigation.util.MyNavHost
import dev.rrohaill.composenavigation.util.setContentFullScreen

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentFullScreen {
            navController = rememberNavController()
            MyNavHost(
                navController = navController,
                startDestination = ScreenOneDestination("I am Screen One"),
            ) {
                mainNavComposables()
            }
        }
    }
}