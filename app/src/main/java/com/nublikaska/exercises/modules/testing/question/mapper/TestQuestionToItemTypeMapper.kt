package com.nublikaska.exercises.modules.testing.question.mapper

import com.nublikaska.exercises.base.test.TestQuestion
import com.nublikaska.exercises.modules.testing.question.vm.types.QuestionItemType

class TestQuestionToItemTypeMapper : (TestQuestion) -> QuestionItemType {

    override fun invoke(question: TestQuestion) = when (question) {

        is TestQuestion.AnswerOptionsQuestion -> QuestionItemType.ANSWER_OPTIONS_QUESTION
        is TestQuestion.AnswerInputQuestion -> QuestionItemType.ANSWER_INPUT_QUESTION
    }
}