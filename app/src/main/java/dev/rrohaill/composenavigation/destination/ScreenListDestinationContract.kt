package dev.rrohaill.composenavigation.destination

import android.os.Parcelable
import androidx.navigation.NavType
import dev.rrohaill.composenavigation.util.Destination
import dev.rrohaill.composenavigation.util.DestinationContract
import dev.rrohaill.composenavigation.util.navTypePairList
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlin.reflect.KType

@Serializable
data object ScreenListDestinationContract : DestinationContract<ScreenListDestination> {
    override fun typeMap(): Map<KType, NavType<*>> {
        return mapOf(navTypePairList<ListData>())
    }
}

@Serializable
data class ScreenListDestination(
    val list: List<ListData>
) : Destination

@Serializable
@Parcelize
data class ListData(
    val name: String
) : Parcelable