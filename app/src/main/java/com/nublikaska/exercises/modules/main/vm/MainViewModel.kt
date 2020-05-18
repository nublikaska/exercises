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
        .addInformation("Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации м Очень много информации Очень много информации Очень много инфо Очень много информациим  Очень много информации рмации м" +
                                "Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации м Очень много информации Очень много информации Очень много инфо Очень много информациим  Очень много информации рмации м\")" +
                                "Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации м Очень много информации Очень много информации Очень много инфо Очень много информациим  Очень много информации рмации м\")" +
                                "Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации м Очень много информации Очень много информации Очень много инфо Очень много информациим  Очень много информации рмации м\")" +
                                "Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации м Очень много информации Очень много информации Очень много инфо Очень много информациим  Очень много информации рмации м\")" +
                                "Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации м Очень много информации Очень много информации Очень много инфо Очень много информациим  Очень много информации рмации м\")" +
                                "Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации м Очень много информации Очень много информации Очень много инфо Очень много информациим  Очень много информации рмации м\")" +
                                "Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации Очень много информации м Очень много информации Очень много информации Очень много инфо Очень много информациим  Очень много информации рмации м\")")
        .addRule("Какое-то правило")
        .addQuestion(
            TestQuestion.AnswerOptionsQuestion(
                questionText = "Что озображено на картинке",
                correctAnswer = "платье",
                answerOptions = listOf("платье", "кофта", "штаны"),
                iconResId = R.drawable.ic_dress
            )
        )
        .addQuestion(
            TestQuestion.AnswerOptionsQuestion(
                questionText = "Как зовут президента РФ",
                correctAnswer = "Владимир",
                answerOptions = listOf("Александр", "Владимир", "Вован", "Хуйван"),
                iconResId = null
            )
        )
        .addQuestion(
            TestQuestion.AnswerOptionsQuestion(
                questionText = "Это уебанский билдер тестов с вариантоами ответов?",
                correctAnswer = "Да",
                answerOptions = listOf("Да", "Нет"),
                iconResId = null
            )
        )
        .setLevel(1)
        .setType(TestType.DEFAULT)
        .setName("Какой-то тест Какой-то тест Какой-то тест Какой-то тест")
        .setDescription("Какое-то описание описание описание описание описание")
        .build(id = "testid1")

    fun closePreview() {

        needShowTestingPreview.value = false
    }

    fun showProfileScreen() = postViewEvents(MainFragmentDirections.actionMainFragmentToProfileFragment().toNavEvent())
}