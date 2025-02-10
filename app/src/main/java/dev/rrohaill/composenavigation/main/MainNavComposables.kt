package dev.rrohaill.composenavigation.main

import dev.rrohaill.composenavigation.main.screens.list.screenListNavComposable
import dev.rrohaill.composenavigation.main.screens.one.screenOneNavComposable
import dev.rrohaill.composenavigation.main.screens.two.screenTwoNavComposable
import dev.rrohaill.composenavigation.util.MyNavGraphBuilder

fun MyNavGraphBuilder.mainNavComposables() {
    screenOneNavComposable()
    screenTwoNavComposable()
    screenListNavComposable()
}