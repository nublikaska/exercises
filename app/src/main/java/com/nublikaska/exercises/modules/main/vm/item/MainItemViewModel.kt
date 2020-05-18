package com.nublikaska.exercises.modules.main.vm.item

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.calculateRating
import com.nublikaska.exercises.base.test.Test
import com.nublikaska.exercises.base.test.TestType
import com.nublikaska.exercises.base.ui.recycler.DiffListItemModel

class MainItemViewModel(

    val isAvailable: LiveData<Boolean>,
    val test: Test

) : DiffListItemModel {

    @DrawableRes
    val iconResId: Int = when (test.type) {
        TestType.DEFAULT -> R.drawable.ic_exclamation_point
        TestType.TRAINING -> R.drawable.ic_question_mark
    }

    val ratingVisible = test.correctAnswersPercent > 0
    val rating = calculateRating(test.correctAnswersPercent)

    override fun isSameAs(other: DiffListItemModel): Boolean {
        return other is MainItemViewModel && other.test.id == test.id
    }
}