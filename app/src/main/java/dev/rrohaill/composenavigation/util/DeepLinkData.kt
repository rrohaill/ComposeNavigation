package dev.rrohaill.composenavigation.util

import android.content.Intent
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkDslBuilder
import androidx.navigation.navDeepLink
import timber.log.Timber

data class DeepLinkData(
    val path: String,
    val hosts: List<String> = listOf("https://links.rrohaill.dev", "https://sandbox-links.rrohaill.dev"),
    val action: String = Intent.ACTION_VIEW,
    val buildType: DeepLinkBuildType = DeepLinkBuildType.AUTOMATIC,
    val deepLinkBuilder: (NavDeepLinkDslBuilder.() -> Unit)? = null
)

enum class DeepLinkBuildType {
    /**
     * Use if you want to provide a base path for deeplink, all properties from KvDestination will be added as params.
     * If class looks like:
     * class A(val p1: String, val p3: String?, val p2: String, val p4: String)
     * first all non-nullable params will be added as extension of path: host/path/{p1}/{p2}
     * then all nullable params, as optional query params using their names as keys:
     * host/path/{p1}/{p2}?p3_name={p3}&p4_name={p4}
     */
    AUTOMATIC,

    /**
     * Allows for overriding custom compose-nav-safe-args behavior, with providing own path.
     * If you want to add required arguments to such query, use {param_name} inside of it.
     */
    CUSTOM
}

inline fun <reified D : Destination> List<DeepLinkData>.mapToComposableNavDeepLinks(
    contract: DestinationContract<D>
) = flatMap { deepLinkData ->
    deepLinkData.hosts.mapNotNull { host ->
        val uri = host.toUri().buildUpon().encodedPath(deepLinkData.path).build()
        uri?.let {
            navDeepLink<D>(basePath = uri.toString(), typeMap = contract.typeMap()) {
                if (deepLinkData.buildType == DeepLinkBuildType.CUSTOM) uriPattern = uri.toString()
                action = deepLinkData.action
                deepLinkData.deepLinkBuilder?.invoke(this)
            }
        }
    }.onEach {
        Timber.d("Deeplink created: ${it.uriPattern}")
    }
}