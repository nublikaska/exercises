package com.nublikaska.exercises.modules.testing.vm

import androidx.lifecycle.MutableLiveData
import com.nublikaska.exercises.R
import com.nublikaska.exercises.base.extensions.sharedViewModel
import com.nublikaska.exercises.base.extensions.toNavEvent
import com.nublikaska.exercises.base.live_data.observeNotNull
import com.nublikaska.exercises.base.test.Test
import com.nublikaska.exercises.base.ui.BaseViewModel
import com.nublikaska.exercises.modules.avatars.data.UserRepository
import com.nublikaska.exercises.modules.testing.question.model.AnsweredQuestion
import com.nublikaska.exercises.modules.testing.shared_vm.TestingSharedViewModel
import com.nublikaska.exercises.modules.testing.ui.TestingFragmentDirections
import java.util.LinkedList

class TestingViewModel(

    userRepository: UserRepository,
    val test: Test

) : BaseViewModel() {

    private val remainingQuestions = LinkedList(test.questions)

    private val answeredQuestion = LinkedList<AnsweredQuestion>()

    private val sharedViewModel by sharedViewModel<TestingSharedViewModel>(R.id.nav_testing)

    val avatarResId = MutableLiveData<Int>()

    val informationVisible = MutableLiveData(test.information.isNullOrEmpty().not())

    val rulesVisible = MutableLiveData(test.rules.isNotEmpty())

    init {
        avatarResId.value = userRepository.avatarId
    }

    override fun onCreate() {
        super.onCreate()

        observeNotNull(sharedViewModel.answer, ::toNextQuestion)
    }

    private fun toNextQuestion(answerForCurrentQuestion: String) {

        answeredQuestion.add(
            AnsweredQuestion(
                question = remainingQuestions.poll() ?: return,
                answer = answerForCurrentQuestion
            )
        )

        val navigationEvent = when (val nextQuestion = remainingQuestions.peek()) {

            null -> TestingFragmentDirections.toTestingFinishFragment(
                testId = test.id,
                questions = answeredQuestion.toTypedArray()
            ).toNavEvent()

            else -> TestingFragmentDirections.toQuestionFragment(

                question = nextQuestion,
                isLastQuestion = remainingQuestions.count() == 1

            ).toNavEvent()
        }

        postViewEvents(navigationEvent)
    }

    fun onCloseRulesClick() {

        rulesVisible.value = false
    }

    fun onCloseInformationClick() {

        informationVisible.value = false
    }
}