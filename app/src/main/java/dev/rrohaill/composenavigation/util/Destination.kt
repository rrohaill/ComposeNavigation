package dev.rrohaill.composenavigation.util

import android.os.Parcelable

/**
 * Marker interface for a destination in a Compose Navigation graph.
 *
 * A class that implements this interface represents a specific screen as a destination in the navigation graph
 * and defines all the necessary parameters for navigating to that screen.
 * Such classes must be annotated with [Serializable], and all parameters should implement [Parcelable]
 * to ensure proper navigation and data passing.
 **/

interface Destination