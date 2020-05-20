package com.nublikaska.exercises.modules.main.vm

import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.toNavEvent
import com.nublikaska.exercises.base.live_data.map
import com.nublikaska.exercises.base.live_data.notNullLiveData
import com.nublikaska.exercises.base.test.TestBuilder
import com.nublikaska.exercises.base.test.TestQuestion
import com.nublikaska.exercises.base.test.TestType
import com.nublikaska.exercises.base.ui.BaseViewModel
import com.nublikaska.exercises.modules.avatars.data.UserRepository
import com.nublikaska.exercises.modules.main.ui.MainFragmentDirections
import com.nublikaska.exercises.modules.main.vm.item.MainItemViewModel
import com.nublikaska.exercises.modules.main.vm.item.MainTestItemClickListener
import com.nublikaska.exercises.modules.repository.test.TestRepository

internal class MainViewModel(

    userRepository: UserRepository,
    private val testRepository: TestRepository

) : BaseViewModel(), MainTestItemClickListener {

    val avatarResId = userRepository.avatarId

    val needShowTestingPreview by notNullLiveData(testRepository.trainingСompleted.not())

    val tests = testRepository.getAllTests().map {

        it.map { test ->

            MainItemViewModel(
                isAvailable = testRepository.currentLevel.map { currentLevel -> currentLevel >= test.level },
                test = test
            )
        }

    }

    override fun onClick(item: MainItemViewModel) {

        postViewEvents(
            MainFragmentDirections.actionMainFragmentToNavTesting(item.test).toNavEvent()
        )
    }

    fun showFirstTesting() {

        postViewEvents(
            MainFragmentDirections.actionMainFragmentToNavTesting(getFirstTest()).toNavEvent()
        )

        testRepository.trainingСompleted = true
        needShowTestingPreview.value = false
    }

    private fun getFirstTest() = TestBuilder()
        .addInformation("Наше приложение поделится с Вами правилами английского языка")
        .addRule("Вам будут представлены различные типы вопросов, с вариантами ответов и без. У ответов с вводом значения регистр не важен!")
        .addQuestion(
            TestQuestion.AnswerOptionsQuestion(
                questionText = "Это вопрос с выбором ответа, выберите любой",
                correctAnswer = "ответ",
                answerOptions = listOf("ответ", "ОТВЕТ"),
                iconResId = null
            )
        )
        .addQuestion(
            TestQuestion.AnswerInputQuestion(
                questionText = "Это вопрос с вводом значение, правильный ответ - ничего",
                correctAnswer = "",
                iconResId = null
            )
        )
        .setLevel(0)
        .setType(TestType.TRAINING)
        .setName("Обучение")
        .build(id = "testTraining1")

    fun closePreview() {

        needShowTestingPreview.value = false
    }

    fun showProfileScreen() = postViewEvents(MainFragmentDirections.actionMainFragmentToProfileFragment().toNavEvent())
}