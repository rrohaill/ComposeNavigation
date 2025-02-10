package dev.rrohaill.composenavigation.util

inline fun <T> Collection<T>.forEachIsLast(onEach: (hasNextItem: Boolean, item: T) -> Unit) {
    forEachIndexed { index, item ->
        val hasNextItem = index >= 0 && index < size - 1
        onEach(hasNextItem, item)
    }
}