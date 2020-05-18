package com.nublikaska.exercises.modules.testing.question.mapper

import com.nublikaska.exercises.base.test.TestQuestion
import com.nublikaska.exercises.modules.testing.question.vm.QuestionViewModel
import com.nublikaska.exercises.modules.testing.question.vm.types.AnswerInputQuestionViewModel
import com.nublikaska.exercises.modules.testing.question.vm.types.OptionAnswersQuestionViewModel

class TestQuestionToViewModelMapper : (TestQuestion) -> QuestionViewModel {

    override fun invoke(question: TestQuestion): QuestionViewModel {

        return when (question) {

            is TestQuestion.AnswerOptionsQuestion -> OptionAnswersQuestionViewModel(question)
            is TestQuestion.AnswerInputQuestion -> AnswerInputQuestionViewModel(question)
        }
    }
}