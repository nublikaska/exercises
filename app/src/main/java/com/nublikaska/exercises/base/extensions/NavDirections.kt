package com.nublikaska.exercises.base.extensions

import androidx.navigation.NavDirections
import com.nublikaska.exercises.base.event.NavEvent

internal fun NavDirections.toNavEvent(): NavEvent = NavEvent.FromDirection(this)