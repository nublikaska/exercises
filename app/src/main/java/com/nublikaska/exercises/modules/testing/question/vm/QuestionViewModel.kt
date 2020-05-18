package com.nublikaska.exercises.modules.testing.question.vm

import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.sharedViewModel
import com.nublikaska.exercises.base.test.TestQuestion
import com.nublikaska.exercises.base.ui.BaseViewModel
import com.nublikaska.exercises.modules.testing.shared_vm.TestingSharedViewModel
import com.nublikaska.exercises.modules.testing.vm.TestingViewModel

abstract class QuestionViewModel : BaseViewModel() {

    abstract val question: TestQuestion

    protected var currentAnswer = ""

    private val sharedViewModel by sharedViewModel<TestingSharedViewModel>(R.id.nav_testing)

    fun toNextQuestion() = sharedViewModel.toNextQuestion(currentAnswer)

    override fun onBackPressed() = Unit
}