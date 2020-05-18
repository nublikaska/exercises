package com.nublikaska.exercises.base.ui.recycler

interface DiffListItemModel {

    fun isSameAs(other: DiffListItemModel): Boolean {

        return other == this
    }
}