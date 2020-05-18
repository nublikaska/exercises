package com.nublikaska.exercises.modules.testing_finish.ui

import androidx.navigation.fragment.navArgs
import com.nublikaska.exercises.R
import com.nublikaska.exercises.BR
import com.nublikaska.exercises.base.extensions.sharedPrefs
import com.nublikaska.exercises.base.extensions.viewModelCreator
import com.nublikaska.exercises.base.ui.BaseFragment
import com.nublikaska.exercises.modules.repository.test.TestRepositoryImpl

class TestingFinishFragment : BaseFragment() {

    private val args by navArgs<TestingFinishFragmentArgs>()

    override val layoutId = R.layout.fragment_testing_finish
    override val dataBindingVariable = BR.vmTestingFinish
    override val viewModel by viewModelCreator {
        TestingFinishViewModel(
            testRepository = TestRepositoryImpl(sharedPrefs),
            testId = args.testId,
            questions = args.questions.toList()
        )
    }
}