package com.nublikaska.exercises

import android.app.Application
import com.google.firebase.FirebaseApp
import com.nublikaska.exercises.base.db.AppDatabaseHolder
import com.nublikaska.exercises.base.store.shared_preferences.AppSharedPrefs
import com.nublikaska.exercises.base.test.Test
import com.nublikaska.exercises.base.test.TestBuilder
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

        GlobalScope.launch(Dispatchers.Main) {

            withContext(IO) { AppDatabaseHolder.instance.clearAllTables() }

            val testRepository = TestRepositoryImpl(AppSharedPrefs(this@App))

            testRepository.clearLevel()

            testRepository.saveTests(
                listOf(
                    TestBuilder()
                        .setLevel(0)
                        .setName("Present Simple")
                        .setDescription("Утвердительные предложения в настоящем простом времени")
                        .addRule(
                            "Время Present Simple обозначает действие в настоящем в широком смысле слова. Оно употребляется для обозначения обычных, регулярно повторяющихся или постоянных действий, например, когда мы говорим о чьих-либо привычках, режиме дня, расписании и т. д., т. е. Present Simple обозначает действия, которые происходят в настоящее время, но не привязаны именно к моменту речи."
                        )
                        .addRule(
                            "Английский глагол во временной форме Present Simple почти всегда совпадает со своей начальной, то есть указанной в словаре, формой без частицы to. Лишь в 3-ем лице единственного числа к ней нужно прибавить окончание -s."
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nI .... French with my friend.",
                                correctAnswer = "study",
                                iconResId = null,
                                answerOptions = listOf("study", "studies")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nMy sister .... a shower every morning.",
                                correctAnswer = "takes",
                                iconResId = null,
                                answerOptions = listOf("take", "takes")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nThis house .... to my grandparents.",
                                correctAnswer = "belongs",
                                iconResId = null,
                                answerOptions = listOf("belong", "belongs")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nWe .... to go for a walk in the evening.",
                                correctAnswer = "like",
                                iconResId = null,
                                answerOptions = listOf("like", "likes")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nMy husband and I .... a lot of money on online games.",
                                correctAnswer = "spend",
                                iconResId = null,
                                answerOptions = listOf("spend", "spends")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nJohn .... coffee twice a day.",
                                correctAnswer = "drinks",
                                iconResId = null,
                                answerOptions = listOf("drink", "drinks")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nIt .... in Russia in winter.",
                                correctAnswer = "snows",
                                iconResId = null,
                                answerOptions = listOf("snow", "snows")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nPlants .... well in a warm climate.",
                                correctAnswer = "grow",
                                iconResId = null,
                                answerOptions = listOf("grow", "grows")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nThey often .... TV at night.",
                                correctAnswer = "watch",
                                iconResId = null,
                                answerOptions = listOf("watch", "watches")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите правильную форму глагола в Present Simple:\n\nHis dog never .... .",
                                correctAnswer = "bark",
                                iconResId = null,
                                answerOptions = listOf("bark", "barks")
                            )
                        )
                        .build("tests1"),

                    TestBuilder()
                        .setLevel(1)
                        .setName("Present Simple")
                        .setDescription("Употребление Present Simple (настоящего простого времени) в пассивном залоге")
                        .addRule(
                            "Чтобы показать, что действие осуществляется над предметом/человеком, в английском языке используется пассивный залог. Посмотрите примеры: «На английском говорят во многих странах. Эти машины производятся в Германии. Этот дом продается». В таких предложениях акцент делается не на том, кто совершает действие, а на том, какое действие совершается над человеком/предметом. Это и есть пассивный (или страдательный) залог.\n\nСамое главное — надо запомнить, что пассивный залог используется, когда мы говорим о действии, которое совершается над человеком/предметом.\n\nМожно выделить следующие случаи его использования: 1. Если мы не знаем, кто совершил действие Например: «Ее кошелек украли» (мы не знаем, кто это сделал). 2. Нам важно само действие, а не тот, кто выполнил его Например: «Часы сделаны в Швейцарии» (нам не важно, кто именно их сделал). 3. Если произошло что-то неприятное, но мы не хотим обвинять кого-то в этом Например: «Праздник испорчен» (мы не хотим говорить, кто конкретно это сделал)"
                        )
                        .addRule(
                            "Пассивный залог в Present Simple образуется с помощью: глагола to be в настоящем времени (am, are, is); глагола в прошедшем времени. В английском языке есть правильные и неправильные глаголы. В зависимости от глагола мы: добавляем окончание -ed, если глагол правильный; ставим его в 3-ю форму, если глагол неправильный.\n\nПримеры:\nThe bread is baked every morning. Этот хлеб выпекают каждое утро. Paper is made from wood. Бумагу делают из древесины. These rooms are cleaned every day. Эти комнаты убирают каждый день."
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nRice (to grow) ... in India.",
                                correctAnswer = "is grown",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nWine (to make) ...",
                                correctAnswer = "is made",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nMuhrooms (to gather) ... in autumn.",
                                correctAnswer = "are gathered",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nEnglish (to speak) ... all over the world.",
                                correctAnswer = "is spoken",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nThe flowers (to water) ... regularly.",
                                correctAnswer = "are watered",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nThis website (to use) ... for learning English",
                                correctAnswer = "is used",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nThe office (to clean) ... every day.",
                                correctAnswer = "is cleaned",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nTheir children (not to allow) ... to go far from home.",
                                correctAnswer = "are not allowed",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nI (not to pay) ... weekly.",
                                correctAnswer = "am not paid",
                                iconResId = null
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerInputQuestion(
                                questionText = "Раскройте скобки, употребляя глаголы в Present Simple Passive:\n\nThe students (to teach) ... by the professor.",
                                correctAnswer = "are taught",
                                iconResId = null
                            )
                        )
                        .build("tests2"),

                    TestBuilder()
                        .setLevel(2)
                        .setName("Личные местоимения")
                        .setDescription("Личные местоимения в именительном падеже")
                        .addRule(
                            "Личные местоимения обозначают лиц или предметы с точки зрения их отношения к говорящему. Например, I обозначает самого говорящего, we – говорящего вместе с другим лицом или лицами, а they – кого-либо помимо говорящего и его собеседника.\nОни могут изменяться по лицам, числу, роду (только в 3-ем лице) и падежу (именительный и объектный).\n\nЕдинственное число:\n1ое лицо: I\n2ое лицо: You\n3е лицо: He(мужской род), She(женский), It(средний)\n\nМножественное число:\n1ое лицо: We\n2ое лицо: You\n3е лицо: They"
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: people",
                                correctAnswer = "they",
                                iconResId = null,
                                answerOptions = listOf("we", "you", "they")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: Nick",
                                correctAnswer = "he",
                                iconResId = null,
                                answerOptions = listOf("he", "she", "it")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: an apple",
                                correctAnswer = "it",
                                iconResId = null,
                                answerOptions = listOf("he", "she", "it")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: animals",
                                correctAnswer = "they",
                                iconResId = null,
                                answerOptions = listOf("you", "they", "we")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: Steve and Mary",
                                correctAnswer = "they",
                                iconResId = null,
                                answerOptions = listOf("he", "we", "they")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: a boy",
                                correctAnswer = "he",
                                iconResId = null,
                                answerOptions = listOf("he", "she", "it")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: a woman",
                                correctAnswer = "she",
                                iconResId = null,
                                answerOptions = listOf("he", "she", "it")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: books",
                                correctAnswer = "they",
                                iconResId = null,
                                answerOptions = listOf("it", "we", "they")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: a fox",
                                correctAnswer = "it",
                                iconResId = null,
                                answerOptions = listOf("he", "she", "it")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Выберите личные местоимения, заменяющие следующие существительные: roses",
                                correctAnswer = "they",
                                iconResId = null,
                                answerOptions = listOf("we", "it", "they")
                            )
                        )
                        .build("tests3"),

                    TestBuilder()
                        .setLevel(3)
                        .setName("Present Simple")
                        .setDescription("Глагол to be в Present Simple")
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "They .... at college.",
                                correctAnswer = "’re",
                                iconResId = null,
                                answerOptions = listOf("’s", "’re", "’m")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Ann .... a teacher.",
                                correctAnswer = "isn’t",
                                iconResId = null,
                                answerOptions = listOf("aren’t", "’m", "isn’t")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "I .... from Paris.",
                                correctAnswer = "’m not",
                                iconResId = null,
                                answerOptions = listOf("’s", "’re", "’m not")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Where .... the keys?",
                                correctAnswer = "are",
                                iconResId = null,
                                answerOptions = listOf("’s", "are", "is")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "John and Mary .... doctors.",
                                correctAnswer = "aren’t",
                                iconResId = null,
                                answerOptions = listOf("is", "isn’t", "aren’t")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "My brother and I .... not students.",
                                correctAnswer = "are",
                                iconResId = null,
                                answerOptions = listOf("am", "is", "are")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "The Statue of Liberty .... in New York.",
                                correctAnswer = "is",
                                iconResId = null,
                                answerOptions = listOf("am not", "am", "is")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "She .... beautiful and clever.",
                                correctAnswer = "’s",
                                iconResId = null,
                                answerOptions = listOf("am", "’s", "’re")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "What colour .... the apples?",
                                correctAnswer = "are",
                                iconResId = null,
                                answerOptions = listOf("am", "is", "are")
                            )
                        )
                        .addQuestion(
                            TestQuestion.AnswerOptionsQuestion(
                                questionText = "Where .... my bag?",
                                correctAnswer = "is",
                                iconResId = null,
                                answerOptions = listOf("am", "is", "are")
                            )
                        )
                        .build("tests4")
                )
            )
        }
    }
}