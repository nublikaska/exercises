package com.nublikaska.exercises.base.event

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

internal sealed class NavEvent : ViewEvent {

    class FromDirection(private val directions: NavDirections) : NavEvent() {

        override fun execute(fragment: Fragment) {

            fragment.findNavController().navigate(directions)
        }
    }

    class UpTo(@IdRes private val resId: Int) : NavEvent() {

        override fun execute(fragment: Fragment) {

            fragment.findNavController().navigate(resId)
        }
    }

    object Back : NavEvent() {

        override fun execute(fragment: Fragment) {

            fragment.findNavController().popBackStack()
        }
    }

    class BackTo(@IdRes val destinationId: Int, val inclusive: Boolean = false) : NavEvent() {

        override fun execute(fragment: Fragment) {

            fragment.findNavController().popBackStack(destinationId, inclusive)
        }
    }

    class SetGraph(@NavigationRes private val graphId: Int) : NavEvent() {

        override fun execute(fragment: Fragment) {

            fragment.findNavController().apply {

                setGraph(graphId)
                navigate(graph.startDestination)
            }
        }
    }
}