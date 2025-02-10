package dev.rrohaill.composenavigation.destination

import androidx.navigation.NavType
import dev.rrohaill.composenavigation.util.Destination
import dev.rrohaill.composenavigation.util.DestinationContract
import kotlinx.serialization.Serializable
import kotlin.reflect.KType

@Serializable
data object ScreenTwoDestinationContract : DestinationContract<ScreenTwoDestination> {
    override fun typeMap(): Map<KType, NavType<*>> {
        return emptyMap<KType, NavType<*>>()
    }
}

@Serializable
data class ScreenTwoDestination(
    val name: String,
    val description: String
) : Destination