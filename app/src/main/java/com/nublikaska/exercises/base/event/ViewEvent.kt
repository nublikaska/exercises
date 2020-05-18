package com.nublikaska.exercises.base.event

import androidx.fragment.app.Fragment

interface ViewEvent {

    fun execute(fragment: Fragment)
}