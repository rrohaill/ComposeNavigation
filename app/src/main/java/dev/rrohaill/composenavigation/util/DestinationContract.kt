package dev.rrohaill.composenavigation.util

import android.os.Parcelable
import androidx.navigation.NavType
import kotlin.reflect.KType

/**
 * Marker interface for a contract for a destination in a Compose Navigation graph.
 *
 * A class that implements this interface holds information necessary to create a specific screen in a graph
 * It provides type mapping for any non-basic types used in the parameters of the `KvDestination`.
 *
 * @param T The type of destination that this contract describes.
 */
interface DestinationContract<T : Destination> {
    /**
     * Returns a map linking the Kotlin types of destination arguments ([KType]) to their respective custom
     * [NavType]. This map may be empty if the type [T] does not use any custom NavTypes.
     * Use [navTypePair] method for all [Parcelable] types to generate the pairs.
     *
     * @return A map where keys are Kotlin types and values are their corresponding NavTypes.
     */
    fun typeMap(): Map<KType, NavType<*>>
}