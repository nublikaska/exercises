package com.nublikaska.exercises.modules.testing.shared_vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nublikaska.exercises.base.ui.BaseViewModel

class TestingSharedViewModel : BaseViewModel() {

    private val _answer = MutableLiveData<String>()

    val answer: LiveData<String>
        get() = _answer

    fun toNextQuestion(answerForCurrentQuestion: String) {

        _answer.value = answerForCurrentQuestion
    }
}