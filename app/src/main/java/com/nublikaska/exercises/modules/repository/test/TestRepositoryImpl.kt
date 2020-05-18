package com.nublikaska.exercises.modules.repository.test

import androidx.lifecycle.LiveData
import com.nublikaska.exercises.base.db.AppDatabaseHolder
import com.nublikaska.exercises.base.db.test.QuestionDb
import com.nublikaska.exercises.base.db.test.TestDb
import com.nublikaska.exercises.base.db.test.TestWithQuestionDb
import com.nublikaska.exercises.base.live_data.filter
import com.nublikaska.exercises.base.live_data.map
import com.nublikaska.exercises.base.store.shared_preferences.SharedPrefs
import com.nublikaska.exercises.base.test.Test
import com.nublikaska.exercises.base.test.TestQuestion
import com.nublikaska.exercises.base.test.TestType
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

private const val CURRENT_LEVEL_KEY = "current_level_key"
private const val TRAINING_COMPLETED_KEY = "training_completed_key"

internal class TestRepositoryImpl(

    private val prefs: SharedPrefs

) : TestRepository {

    private val testDao = AppDatabaseHolder.instance.testDao()

    override var trainingСompleted: Boolean
        get() = prefs.getBoolean(TRAINING_COMPLETED_KEY, false)
        set(value) = prefs.put(TRAINING_COMPLETED_KEY, value)

    override val currentLevel = prefs.getLiveInt(CURRENT_LEVEL_KEY, 0)

    override fun upCurrentLevel() {

        val nextValue = currentLevel.value!! + 1
        prefs.put(CURRENT_LEVEL_KEY, nextValue)
    }

    override fun clearLevel() = prefs.put(CURRENT_LEVEL_KEY, 0)

    override suspend fun getTestById(id: String): Test? = withContext(IO) { testDao.getById(id)?.mapFromDb() }

    override fun getAllTests(): LiveData<List<Test>> = testDao.getAllTestsWithQuestions().map {

        it.map { testWithQuestionDb -> testWithQuestionDb.mapFromDb() }
    }

    private fun TestWithQuestionDb.mapFromDb() = Test(
        id = test.id,
        name = test.name,
        description = test.description,
        level = test.level,
        type = TestType.valueOf(test.type),
        passed = test.passed,
        information = test.information,
        rules = test.rules,
        correctAnswersPercent = test.correctAnswersPercent,
        questions = questions.map { questionDb ->

            when (questionDb.answerOptions.isEmpty()) {

                true -> TestQuestion.AnswerInputQuestion(
                    questionText = questionDb.questionText,
                    correctAnswer = questionDb.correctAnswer,
                    iconResId = questionDb.iconResId
                )

                else -> TestQuestion.AnswerOptionsQuestion(
                    questionText = questionDb.questionText,
                    correctAnswer = questionDb.correctAnswer,
                    iconResId = questionDb.iconResId,
                    answerOptions = questionDb.answerOptions
                )
            }
        }
    )

    override fun getAllPassedRules(): LiveData<List<String>> {

        return getAllTests()
            .filter { it.passed }
            .map {

                val allRules = mutableListOf<String>()
                it.forEach { test -> allRules.addAll(test.rules) }
                return@map allRules
            }
    }

    override suspend fun saveTests(tests: List<Test>) = withContext(IO) {

        testDao.saveAllTests(tests.map { test ->
            TestDb().apply {
                id = test.id
                name = test.name
                description = test.description
                level = test.level
                type = test.type.name
                information = test.information
                rules = test.rules
                correctAnswersPercent = test.correctAnswersPercent
                passed = test.passed
            }
        })

        tests.forEach { test ->

            testDao.saveAllQuestions(test.questions.map { question ->

                QuestionDb().apply {
                    testId = test.id
                    questionText = question.questionText
                    correctAnswer = question.correctAnswer
                    iconResId = question.iconResId
                    answerOptions = when (question) {
                        is TestQuestion.AnswerOptionsQuestion -> question.answerOptions
                        is TestQuestion.AnswerInputQuestion -> emptyList()
                    }
                }
            })
        }
    }

    override suspend fun updateByIds(passed: Boolean, correctAnswersPercent: Float, ids: List<String>) {
        withContext(IO) {
            testDao.updatePassedByIds(
                passed = passed,
                correctAnswersPercent = correctAnswersPercent,
                ids = ids
            )
        }
    }
}