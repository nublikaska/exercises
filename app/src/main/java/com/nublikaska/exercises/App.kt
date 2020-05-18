package com.nublikaska.exercises

import android.app.Application
import com.google.firebase.FirebaseApp
import com.nublikaska.exercises.base.db.AppDatabaseHolder
import com.nublikaska.exercises.base.store.shared_preferences.AppSharedPrefs
import com.nublikaska.exercises.base.test.Test
import com.nublikaska.exercises.base.test.TestQuestion
import com.nublikaska.exercises.base.test.TestType
import com.nublikaska.exercises.modules.avatars.data.UserRepositoryImpl
import com.nublikaska.exercises.modules.repository.test.TestRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        AppDatabaseHolder.initDb(this)

        val userRepository = UserRepositoryImpl(AppSharedPrefs(this@App))
        if (userRepository.userName.isEmpty()) initializeTests()
    }

    private fun initializeTests() {

        val baseTest = Test(
            id = "id1",
            name = "Тест",
            description = "Описание теста",
            imageUrl = "http://dotnetuniversity.com/wp-content/uploads/2019/12/usertesting-730x356.jpg",
            level = 0,
            type = TestType.DEFAULT,
            passed = false,
            information = null,
            rules = listOf(
                "Правило 1",
                "Правило 2",
                "Правило 3",
                "Правило 4",
                "Правило 5",
                "Правило 6",
                "Правило 7",
                "Правило 8",
                "Правило 9",
                "Правило 10",
                "Правило 11",
                "Правило 12",
                "Правило 13",
                "Правило 14",
                "Правило 15",
                "Правило 16",
                "Правило 17",
                "Правило 18",
                "Правило 19",
                "Правило 20"
            ),
            questions = listOf(
                TestQuestion.AnswerOptionsQuestion(
                    questionText = "Что озображено на картинке",
                    correctAnswer = "платье",
                    answerOptions = listOf("платье", "кофта", "штаны"),
                    iconResId = R.drawable.ic_dress
                ),
                TestQuestion.AnswerOptionsQuestion(
                    questionText = "Как зовут президента РФ",
                    correctAnswer = "Владимир",
                    answerOptions = listOf("Александр", "Владимир", "Вован", "Хуйван"),
                    iconResId = null
                ),
                TestQuestion.AnswerInputQuestion(
                    questionText = "Как зовут создателя",
                    correctAnswer = "denis",
                    iconResId = null
                )
            )
        )

        GlobalScope.launch(Dispatchers.Main) {

            withContext(IO) { AppDatabaseHolder.instance.clearAllTables() }

            val testRepository = TestRepositoryImpl(AppSharedPrefs(this@App))

            testRepository.clearLevel()

            testRepository.saveTests(
                listOf(
                    baseTest.copy(id = "id1", type = TestType.TRAINING, level = 0),
                    baseTest.copy(id = "id2", level = 1, name = "Тест 1", information = "Мало информации"),
                    baseTest.copy(id = "id3", level = 2, name = "Тест 2"),
                    baseTest.copy(id = "id4", level = 2, name = "Тест 2"),
                    baseTest.copy(id = "id5", level = 4, name = "Тест 4")
                )
            )
        }
    }
}