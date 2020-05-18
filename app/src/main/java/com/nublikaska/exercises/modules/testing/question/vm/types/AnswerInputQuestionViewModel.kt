package com.nublikaska.exercises.modules.testing.question.vm.types

import com.nublikaska.exercises.base.live_data.map
import com.nublikaska.exercises.base.live_data.notNullLiveData
import com.nublikaska.exercises.base.test.TestQuestion
import com.nublikaska.exercises.modules.testing.question.vm.QuestionViewModel

class AnswerInputQuestionViewModel(

    override val question: TestQuestion.AnswerInputQuestion

) : QuestionViewModel() {

    val answer by notNullLiveData("") { currentAnswer = it }

    val canContinue = answer.map { it.isNotEmpty() }
}