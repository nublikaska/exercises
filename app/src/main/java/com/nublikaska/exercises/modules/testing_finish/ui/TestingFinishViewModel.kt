package com.nublikaska.exercises.modules.testing_finish.ui

import com.nublikaska.exercises.base.event.NavEvent
import com.nublikaska.exercises.base.extensions.calculateRating
import com.nublikaska.exercises.base.ui.BaseViewModel
import com.nublikaska.exercises.modules.repository.test.TestRepository
import com.nublikaska.exercises.modules.testing.question.model.AnsweredQuestion
import kotlinx.coroutines.launch

class TestingFinishViewModel(

    testRepository: TestRepository,
    testId: String,
    questions: List<AnsweredQuestion>

) : BaseViewModel() {

    private val correctAnswerCount = questions.count {

        it.answer.equals(it.question.correctAnswer, ignoreCase = true)
    }

    private val totalQuestionCount = questions.count()

    private val correctAnswersPercent = correctAnswerCount.toFloat() / totalQuestionCount.toFloat() * 100
    private val passed = correctAnswersPercent > 50

    val rating = calculateRating(correctAnswersPercent)

    val subtitle = "Вы ответили правильно на $correctAnswerCount из $totalQuestionCount вопросов"

    init {

        launch {

            val currentTest = testRepository.getTestById(testId) ?: return@launch

            if (passed && currentTest.passed.not()) testRepository.upCurrentLevel()
            if (correctAnswersPercent > currentTest.correctAnswersPercent) testRepository.updateByIds(
                passed = passed,
                correctAnswersPercent = correctAnswersPercent,
                ids = listOf(testId)
            )
        }
    }

    fun toMainScreen() = postViewEvents(NavEvent.Back)
}