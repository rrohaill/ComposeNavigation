package dev.rrohaill.composenavigation.util

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.CollectionNavType
import androidx.navigation.NavType
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

inline fun <reified T : Parcelable?> navType(isNullableAllowed: Boolean = false) =
    object : NavType<T>(
        isNullableAllowed = isNullableAllowed
    ) {
        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }

        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getCompatParcelableExtra<T>(key)
        }

        override fun serializeAsValue(value: T): String {
            // Serialized values must always be Uri encoded
            return Uri.encode(Json.encodeToString(value))
        }

        override fun parseValue(value: String): T {
            // Navigation takes care of decoding the string
            // before passing it to parseValue()
            return Json.decodeFromString<T>(value)
        }
    }

inline fun <reified T : Parcelable?> navTypePair(isNullableAllowed: Boolean = false) =
    typeOf<T>() to navType<T>(isNullableAllowed)

inline fun <reified T : Parcelable> navTypePairList(isNullableAllowed: Boolean = false) =
    typeOf<List<T>>() to navTypeList<T>(isNullableAllowed)

inline fun <reified T : Parcelable> navTypeList(
    isNullableAllowed: Boolean = false
) = object : CollectionNavType<List<T>>(isNullableAllowed) {
    val json: Json = Json
    override fun emptyCollection(): List<T> = emptyList()

    override fun get(bundle: Bundle, key: String): List<T>? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelableArray(key, T::class.java)?.toList()
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelableArray(key)?.map { it as T }?.toList()
        }

    override fun parseValue(value: String): List<T> = json.decodeFromString(value)

    override fun parseValue(value: String, previousValue: List<T>): List<T> =
        previousValue.plus(json.decodeFromString<T>(value))

    override fun serializeAsValues(value: List<T>): List<String> =
        value.map { json.encodeToString(it) }

    override fun put(bundle: Bundle, key: String, value: List<T>) {
        bundle.putParcelableArray(key, value.toTypedArray())
    }
}