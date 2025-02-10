package dev.rrohaill.composenavigation.destination

import android.os.Parcelable
import androidx.navigation.NavType
import dev.rrohaill.composenavigation.util.Destination
import dev.rrohaill.composenavigation.util.DestinationContract
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlin.reflect.KType

@Serializable
data object ScreenOneDestinationContract : DestinationContract<ScreenOneDestination> {
    override fun typeMap(): Map<KType, NavType<*>> {
        return emptyMap<KType, NavType<*>>()
    }
}

@Serializable
data class ScreenOneDestination(
    val name: String
) : Destination

@Parcelize
data class ScreenOneResult(
    val data: String
) : Parcelable