package com.nublikaska.exercises.modules.testing.question.vm.types

import com.nublikaska.exercises.base.test.TestQuestion
import com.nublikaska.exercises.modules.testing.question.vm.QuestionViewModel

class OptionAnswersQuestionViewModel(

    override val question: TestQuestion.AnswerOptionsQuestion

) : QuestionViewModel() {

    fun onSelectAnswer(answer: String) {

        currentAnswer = answer
    }
}