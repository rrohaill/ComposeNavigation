package dev.rrohaill.composenavigation.util

import android.os.Build
import android.os.Bundle

inline fun <reified T> Bundle?.getCompatParcelableExtra(keyName: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getParcelable(keyName, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        this?.getParcelable(keyName)
    }
}